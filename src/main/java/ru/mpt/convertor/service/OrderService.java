package ru.mpt.convertor.service;

import ru.mpt.convertor.exception.EmptyCartException;
import ru.mpt.convertor.exception.UnknownEntityException;
import ru.mpt.convertor.model.Order;
import ru.mpt.convertor.model.StatusOrder;

import java.util.List;

public interface OrderService {
    List<Order> getUserOrders(String userEmail);

    Order getUserOrder(String userEmail, int orderId) throws UnknownEntityException;

    Order createUserOrder(String userEmail) throws EmptyCartException;

    boolean updateStatus(int orderId, StatusOrder status);

    boolean finishOrder(int orderId);

    List<Order> getAll();

    List<Order> getAllByStatus(StatusOrder status);
}
