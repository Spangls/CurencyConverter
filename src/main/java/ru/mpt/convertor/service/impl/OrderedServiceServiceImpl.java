package ru.mpt.convertor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpt.convertor.model.OrderedService;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.repos.ServiceRepo;
import ru.mpt.convertor.service.OrderedServiceService;
import ru.mpt.convertor.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderedServiceServiceImpl  implements OrderedServiceService {

    private final ServiceRepo serviceRepo;
    private final UserService userService;

    public OrderedServiceServiceImpl(ServiceRepo serviceRepo, UserService userService) {
        this.serviceRepo = serviceRepo;
        this.userService = userService;
    }

    @Override
    public List<OrderedService> getAll() {
        return serviceRepo.findAll();
    }

    @Override
    public List<OrderedService> getAllByState(Boolean active) {
        return serviceRepo.findAllByActive(active);
    }

    @Transactional
    @Override
    public OrderedService createOrderedService(String userEmail, ru.mpt.convertor.model.Service serviceType) {
        OrderedService  service = createNewService(userEmail, serviceType);
        if (service  == null)
            return null;
        return serviceRepo.save(service);
    }

    @Transactional
    @Override
    public boolean finishOrderedService(int orderedServiceId) {
        OrderedService service = serviceRepo.findById(orderedServiceId).orElse(null);
        if (service == null)
            return false;
        service.setActive(false);
        serviceRepo.save(service);
        return true;
    }

    private OrderedService createNewService(String userEmail, ru.mpt.convertor.model.Service serviceType){
        User user  = userService.findByEmail(userEmail);
        if (user == null)
            return null;
        OrderedService service = new OrderedService();
        service.setActive(true);
        service.setDate(LocalDate.now());
        service.setService(serviceType);
        service.setUser(user);
        return service;
    }
}
