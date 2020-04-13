package ru.mpt.convertor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpt.convertor.model.Motherboard;
import ru.mpt.convertor.model.Ram;
import ru.mpt.convertor.model.RamTech;
import ru.mpt.convertor.repos.ItemRepo;
import ru.mpt.convertor.repos.MotherboardRepo;
import ru.mpt.convertor.repos.RamRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ConfiguratorController {

    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private RamRepo ramRepo;
    @Autowired
    private MotherboardRepo mbRepo;

    @GetMapping("/configurator")
    public String configurator(Model model){
        model.addAttribute("ramList", ramRepo.findAll());
        model.addAttribute("mbList", mbRepo.findAll());
        return "configurator";
    }

    public String configurator(
            @RequestParam(required = false) String chipset,
            @RequestParam(required = false) String mbFF,
            @RequestParam(required = false) String ramTech,
            @RequestParam(required = false) String socket,
            Model model){
        model.addAttribute("ramList", ramRepo.findAllByRamTech(RamTech.valueOf(ramTech)));
        List<Motherboard> mbList = new ArrayList<>();
        return "configurator";
    }
}
