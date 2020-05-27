package ru.mpt.convertor.controller.frontend;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.mpt.convertor.dto.assembler.CartDtoAssembler;
import ru.mpt.convertor.exception.EmptyCartException;
import ru.mpt.convertor.model.*;
import ru.mpt.convertor.service.CartService;
import ru.mpt.convertor.service.MailSender;
import ru.mpt.convertor.service.OrderService;
import ru.mpt.convertor.service.UserService;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/checkout")
@Secured({"ROLE_USER", "ROLE_EMPLOYEE", "ROLE_ADMIN"})
public class CheckoutController {

    private final static String CHECKOUT_BASE = "checkout";
    private final static String CHECKOUT_DETAILS = CHECKOUT_BASE + "/details";
    private final static String CHECKOUT_CONFIRM = CHECKOUT_BASE + "/confirmation";

    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService;
    private final MailSender mailSender;
    private final CartDtoAssembler cartDtoAssembler = new CartDtoAssembler();

    public CheckoutController(CartService cartService, UserService userService, OrderService orderService, MailSender mailSender) {
        this.cartService = cartService;
        this.userService = userService;
        this.orderService = orderService;
        this.mailSender = mailSender;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        if (user == null)
            return "redirect:/cart";

        Cart cart = cartService.getCartOrCreate(email);
        Map<String, Item> itemsById = collectItemsMap(cart);

        model.addAttribute("itemsById", itemsById);
        model.addAttribute("cart", cartDtoAssembler.toModel(cart));
        return CHECKOUT_DETAILS;
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String createOrder(Principal principal, Model model) {
        String email = principal.getName();
        try {
            Order order = orderService.createUserOrder(email);

            StringBuilder message = new StringBuilder();
            message.append("Благодарим вас за выбор нашего магазина! \n");
            message.append("Ваш заказ будет готов к выдаче в течение 24 часов. \n");
            message.append("Итоговая сумма к оплате: ").append(order.getCost()).append(" руб.\n");
            message.append("Список товаров: \n");
            order.getOrderedItems().forEach(orderedItem ->
                    message.append(orderedItem.getItem().getManufacturer().getTitle()).append(" ").
                            append(orderedItem.getItem().getName()).append(" ").
                            append(orderedItem.getItem().getPrice()).append(" руб. * ").
                            append(orderedItem.getQuantity()).append(" = ").
                            append(orderedItem.getQuantity() * orderedItem.getItem().getPrice()).append(" руб.\n"));
            mailSender.send(email, "Детали заказа №" + order.getId().toString(), message.toString());
            return "redirect:/" + CHECKOUT_CONFIRM + "/" + order.getId();
        } catch (EmptyCartException e) {
            return "redirect:/cart";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String confirmOrder(Model model, @PathVariable Order order){

        return "";
    }

    @RequestMapping(value = "/confirmation/{order}", method = RequestMethod.GET)
    public String getGratitude(Principal principal, Model model, @PathVariable Order order) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        if (user == null)
            return "redirect:/cart";

        model.addAttribute("createdOrder", order);
        model.addAttribute("user", user);
        return CHECKOUT_CONFIRM;
    }


    private Map<String, Item> collectItemsMap(Cart cart) {
        return cart.getCartItems().stream()
                .map(CartItem::getItem)
                .collect(Collectors.toMap(item -> item.getId().toString(), i -> i));
    }
}
