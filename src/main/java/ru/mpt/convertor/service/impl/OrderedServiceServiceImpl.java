package ru.mpt.convertor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpt.convertor.model.Order;
import ru.mpt.convertor.model.OrderedService;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.repos.ServiceRepo;
import ru.mpt.convertor.service.MailSender;
import ru.mpt.convertor.service.OrderedServiceService;
import ru.mpt.convertor.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderedServiceServiceImpl  implements OrderedServiceService {

    private final ServiceRepo serviceRepo;
    private final UserService userService;
    private final MailSender mailSender;

    public OrderedServiceServiceImpl(ServiceRepo serviceRepo, UserService userService, MailSender mailSender) {
        this.serviceRepo = serviceRepo;
        this.userService = userService;
        this.mailSender =  mailSender;
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
        mailSender.send(service.getUser().getEmail(), "Готовность заказа", getFinishMailText(service));
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

    private String getFinishMailText(OrderedService service){
        return String.format("Доброго времени суток, %s!\n" +
                "Спешим оповестить вас, что ваш заказ №%s от %s готов к выдаче.\n", service.getUser().getFirstName(), service.getId(), service.getDate());
    }
}
