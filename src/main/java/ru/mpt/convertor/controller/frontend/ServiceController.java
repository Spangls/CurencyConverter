package ru.mpt.convertor.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpt.convertor.model.*;
import ru.mpt.convertor.service.OrderedServiceService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceController {

    private final OrderedServiceService orderedServiceService;

    public ServiceController(OrderedServiceService orderedServiceService) {
        this.orderedServiceService = orderedServiceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getServicesView(Model model){
        model.addAttribute("serviceTypes", Service.values());
        return "service";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createService(Principal principal, @RequestParam Service serviceType, Model model){
        model.addAttribute("serviceTypes", Service.values());
        String userEmail = principal.getName();
        if (userEmail == null || userEmail.trim().isEmpty()) {
            model.addAttribute("success", false);
            return "service";
        }
        model.addAttribute("success", orderedServiceService.createOrderedService(userEmail, serviceType) != null);
        return "service";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public String getServiceAll(Model model){
        model.addAttribute("services", orderedServiceService.getAll());
        return "orders";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/all/ready")
    public String getServiceComplete(Model model) {
        model.addAttribute("services", orderedServiceService.getAllByState(false));
        return "orders";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all/processed")
    public String getServiceProcessed(Model model) {
        model.addAttribute("services", orderedServiceService.getAllByState(true));
        return "orders";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"all", "/all/{state}"})
    public String getServices(@PathVariable(required = false, name = "state") String state, @RequestParam int serviceId, Model model){
        if (!orderedServiceService.finishOrderedService(serviceId))
            model.addAttribute("message", "Что-то пошло не так. Попробуйте позже!");
        List<OrderedService> services = null;
        if (state == null)
            services = orderedServiceService.getAll();
        if (state != null && state.equals("ready"))
            services = orderedServiceService.getAllByState(false);
        if (state != null && state.equals("processed"))
            services =orderedServiceService.getAllByState(true);
        model.addAttribute("services", services);
        return "orders";
    }
}
