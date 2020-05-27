package ru.mpt.convertor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpt.convertor.exception.EmptyCartException;
import ru.mpt.convertor.exception.UnknownEntityException;
import ru.mpt.convertor.model.*;
import ru.mpt.convertor.repos.OrderRepo;
import ru.mpt.convertor.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final CartService cartService;
    private final UserService userService;
    private final CountService countService;
    private final MailSender mailSender;

    public OrderServiceImpl(OrderRepo orderRepo, CartService cartService, UserService userService, CountService countService, MailSender mailSender) {
        this.orderRepo = orderRepo;
        this.cartService = cartService;
        this.userService = userService;
        this.countService = countService;
        this.mailSender = mailSender;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAllByStatus(StatusOrder status) {
        return orderRepo.findAllByStatus(status);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getUserOrders(String userEmail) {
        return orderRepo.findByUserOrderByDateDesc(userService.findByEmail(userEmail));
    }

    @Transactional(readOnly = true)
    @Override
    public Order getUserOrder(String userEmail, int orderId) throws UnknownEntityException {
        Order order = orderRepo.findById(orderId).orElse(null);
        if ((order == null) || !order.getUser().getEmail().equals(userEmail))
            throw new UnknownEntityException(Order.class, orderId);
        return order;
    }

    @Transactional
    @Override
    public Order createUserOrder(String userEmail) throws EmptyCartException {
        Cart cart = cartService.getCartOrCreate(userEmail);
        if (cart.isEmpty())
            throw new EmptyCartException();

        Order order = createNewOrder(userEmail, cart);
        orderRepo.saveAndFlush(order);
        fillOrderedItems(cart, order);
        orderRepo.save(order);
        cartService.clearCart(userEmail);
        return order;
    }

    @Transactional
    @Override
    public boolean updateStatus(int orderId, StatusOrder status) {
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order == null)
            return false;
        order.setStatus(status);
        orderRepo.save(order);
        return true;
    }

    @Transactional
    @Override
    public boolean finishOrder(int orderId) {
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order == null)
            return false;
        order.getOrderedItems().forEach(orderedItem -> {
            countService.save(new Count(orderedItem.getItem(), orderedItem.getQuantity()));
        });
        order.setStatus(StatusOrder.READY);
        orderRepo.save(order);
        mailSender.send(order.getUser().getEmail(), "Готовность заказа", getFinishMailText(order));
        return true;
    }

    private Order createNewOrder(String userEmail, Cart cart) {
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setStatus(StatusOrder.IN_PROCESS);
        order.setUser(userService.findByEmail(userEmail));
        order.setCost(cart.getItemsCost());
        return order;
    }

    private void fillOrderedItems(Cart cart, Order order) {
        Set<OrderedItem> ordered = cart.getCartItems().stream()
                .map(item -> createOrderedItem(order, item))
                .collect(toSet());
        order.setOrderedItems(ordered);
    }

    private OrderedItem createOrderedItem(Order order, CartItem item) {
        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setItem(item.getItem());
        orderedItem.setOrder(order);
        orderedItem.setQuantity(item.getQuantity());
        return orderedItem;
    }

    private String getFinishMailText(Order order){
        return String.format("Доброго времени суток, %s!\n" +
                "Спешим оповестить вас, что ваш заказ №%s от %s готов к выдаче.\n", order.getUser().getFirstName(), order.getId(), order.getDate());
    }
}
