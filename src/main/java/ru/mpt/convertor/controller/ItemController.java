package ru.mpt.convertor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mpt.convertor.model.Manufacturer;
import ru.mpt.convertor.repos.ManufacturerRepo;

@Controller
public class ItemController {

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @GetMapping("/manufacturer")
    public String manufacturer(){
        return "manufacturer";
    }

    @PostMapping("/manufacturer")
    public String addManufacturer(Manufacturer manufacturer, Model model){
        if (manufacturerRepo.findFirstByTitle(manufacturer.getTitle()) != null){
            model.addAttribute("message", "Данный производитель уже в базе данных.");
            return "manufacturer";
        }
        manufacturerRepo.save(manufacturer);
        return "manufacturer";
    }

    @GetMapping("/item")
    public String item(){
        return "item";
    }

    @PostMapping("/item")
    public String addItem(Manufacturer manufacturer, Model model){
        if (manufacturerRepo.findFirstByTitle(manufacturer.getTitle()) != null){
            model.addAttribute("message", "Данный производитель уже в базе данных.");
            return "manufacturer";
        }
        manufacturerRepo.save(manufacturer);
        return "manufacturer";
    }
}
