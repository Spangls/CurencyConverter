package ru.mpt.convertor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.convertor.model.*;
import ru.mpt.convertor.repos.*;

import java.util.*;

@Controller
public class ConfiguratorController {

    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private PriceRepo priceRepo;

    @Autowired
    private CaseRepo caseRepo;
    @Autowired
    private CpuRepo cpuRepo;
    @Autowired
    private GpuRepo gpuRepo;
    @Autowired
    private HDRepo hdRepo;
    @Autowired
    private MotherboardRepo mbRepo;
    @Autowired
    private PowerSupplyRepo psRepo;
    @Autowired
    private RamRepo ramRepo;

    @GetMapping("/configurator")
    public String configurator(Model model) {
        model.addAttribute("caseList", caseRepo.findAll());
        model.addAttribute("cpuList", cpuRepo.findAll());
        model.addAttribute("gpuList", gpuRepo.findAll());
        model.addAttribute("hdList", hdRepo.findAll());
        model.addAttribute("mbList", mbRepo.findAll());
        model.addAttribute("psList", psRepo.findAll());
        model.addAttribute("ramList", ramRepo.findAll());
        return "configurator";
    }

    @PostMapping("/configurator")
    public @ResponseBody
    Map<String, Object> configurator(
            Integer caseId,
            Integer cpuId,
            Integer gpuId,
            Integer hdId,
            Integer mbId,
            Integer psId,
            Integer ramId) {
        Map<String, Object> response = new TreeMap<>();
        Case casePc = caseRepo.findFirstById(caseId);
        Cpu cpu = cpuRepo.findFirstById(cpuId);
        Gpu gpu = gpuRepo.findFirstById(gpuId);
        HardDrive hd = hdRepo.findFirstById(hdId);
        Motherboard mb = mbRepo.findFirstById(mbId);
        PowerSupply ps = psRepo.findFirstById(psId);
        Ram ram = ramRepo.findFirstById(ramId);

        String price = calculatePrice(
                casePc == null? 0 : findPrice(casePc.getItem().getId()),
                cpu == null? 0 : findPrice(cpu.getItem().getId()),
                gpu == null? 0 : findPrice(gpu.getItem().getId()),
                hd == null? 0 : findPrice(hd.getItem().getId()),
                mb == null? 0 : findPrice(mb.getItem().getId()),
                ps == null? 0 : findPrice(ps.getItem().getId()),
                ram == null? 0 : findPrice(ram.getItem().getId()));

        response.put("caseList", mb == null ? caseRepo.findAll() : caseRepo.findAllByMotherboardFormFactor(mb.getFormFactor()));
        response.put("cpuList", mb == null ? cpuRepo.findAll() : cpuRepo.findAllBySocket(mb.getSocket()));
        response.put("gpuList", gpuRepo.findAll());
        response.put("hdList", hdRepo.findAll());
        response.put("mbList", findMotherboards(ram, cpu, casePc));
        response.put("psList", psRepo.findAll());
        response.put("ramList", mb == null ? ramRepo.findAll() : ramRepo.findAllByRamTech(mb.getRamTech()));
        response.put("price", price);
        return response;
    }

    private Set<Motherboard> findMotherboards(Ram ram, Cpu cpu, Case case_){
        if (ram == null) {
            if (cpu == null) {
                if (case_ == null)
                    return mbRepo.findAll();
                return mbRepo.findAllByFormFactor(case_.getMotherboardFormFactor());
            }
            if (case_ == null)
                return mbRepo.findAllBySocket(cpu.getSocket());
            return mbRepo.findAllBySocketAndFormFactor(cpu.getSocket(), case_.getMotherboardFormFactor());
        }
        if (cpu == null) {
            if (case_ == null)
                return mbRepo.findAllByRamTech(ram.getRamTech());
            return mbRepo.findAllByRamTechAndFormFactor(ram.getRamTech(), case_.getMotherboardFormFactor());
        }
        if (case_ == null)
            return mbRepo.findAllByRamTechAndSocket(ram.getRamTech(), cpu.getSocket());
        return mbRepo.findAllByRamTechAndSocketAndFormFactor(ram.getRamTech(), cpu.getSocket(), case_.getMotherboardFormFactor());
    }
    private Float findPrice(Integer itemId){
        Price price = priceRepo.findFirstByItemIdOrderByDateDesc(itemId);
        return price == null ? 0 : price.getPrice();
    }
    private String calculatePrice(Float caseP, Float cpuP, Float gpuP, Float hdP, Float mbP, Float psP, Float ramP){
        return "<h3>Стоимость: "+(caseP+cpuP+gpuP+hdP+mbP+psP+ramP)+"</h3>"+
                "<p>Корпус: "+caseP+"</p>"+
                "<p>Процессор: "+cpuP+"</p>"+
                "<p>Видеокарта: "+gpuP+"</p>"+
                "<p>Накопитель информации: "+hdP+"</p>"+
                "<p>Материнская плата: "+mbP+"</p>"+
                "<p>Блок питания: "+psP+"</p>"+
                "<p>Оперативная память: "+ramP+"</p>";
    }
}
