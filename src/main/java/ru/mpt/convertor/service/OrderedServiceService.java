package ru.mpt.convertor.service;

import ru.mpt.convertor.model.OrderedService;
import ru.mpt.convertor.model.Service;

import java.util.List;

public interface OrderedServiceService {
    List<OrderedService> getAll();
    List<OrderedService> getAllByState(Boolean active);
    OrderedService createOrderedService(String userEmail, Service serviceType);
    boolean finishOrderedService(int orderedServiceId);
}
