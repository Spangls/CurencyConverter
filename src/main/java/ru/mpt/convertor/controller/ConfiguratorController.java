package ru.mpt.convertor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpt.convertor.model.Ram;
import ru.mpt.convertor.repos.ItemRepo;
import ru.mpt.convertor.repos.RamRepo;

@Controller
@RequestMapping("/configurator")
public class ConfiguratorController {

    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private RamRepo ramRepo;

    @GetMapping("configurator")
    public String configurator(){
        return "configurator";
    }

    public String configurator(
            @RequestParam(required = false) Integer cpuId,
            @RequestParam(required = false) Integer gpuId,
            @RequestParam(required = false) Integer ramId,
            @RequestParam(required = false) Integer mbId,
            @RequestParam(required = false) Integer psId,
            @RequestParam(required = false) Integer caseId,
            @RequestParam(required = false) Integer hdId,
            Model model){
        model.addAttribute("ramList", ramRepo.findAll());
        return "configurator";
    }
}
