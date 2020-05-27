package ru.mpt.convertor.controller.backend;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.convertor.model.Order;
import ru.mpt.convertor.model.StatusOrder;
import ru.mpt.convertor.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee/order")
@Secured({"ROLE_EMPLOYEE"})
public class OrderController {
    private static final String ORDER_BASE = "employee/order";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getOrdersAll(Model model){
        model.addAttribute("orders", orderService.getAll());
        return "orders";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/ready")
    public String getOrdersComplete(Model model) {
        model.addAttribute("orders", orderService.getAllByStatus(StatusOrder.READY));
        return "orders";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/processed")
    public String getOrdersProcessed(Model model) {
        model.addAttribute("orders", orderService.getAllByStatus(StatusOrder.IN_PROCESS));
        return "orders";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"", "/{state}"})
    public String confirmOrder(@PathVariable(required = false, name = "state") String state, @RequestParam int orderId, Model model) {
        if (!orderService.finishOrder(orderId))
            model.addAttribute("message", "Что-то пошло не так. Попробуйте позже!");
        List<Order> orders = null;
        if (state == null)
            orders = orderService.getAll();
        if (state != null && state.equals("ready"))
            orders = orderService.getAllByStatus(StatusOrder.READY);
        if (state != null && state.equals("processed"))
            orders =orderService.getAllByStatus(StatusOrder.IN_PROCESS);
        model.addAttribute("orders", orders);
        return "orders";
    }
}
