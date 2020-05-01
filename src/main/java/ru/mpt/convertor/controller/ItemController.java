package ru.mpt.convertor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mpt.convertor.common.Utility;
import ru.mpt.convertor.model.*;
import ru.mpt.convertor.repos.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CpuRepo cpuRepo;
    @Autowired
    private CoolingSystemRepo coolingSystemRepo;
    @Autowired
    private CaseRepo caseRepo;
    @Autowired
    private GpuRepo gpuRepo;
    @Autowired
    private HDRepo hdRepo;
    @Autowired
    private MotherboardRepo motherboardRepo;
    @Autowired
    private PowerSupplyRepo powerSupplyRepo;
    @Autowired
    private RamRepo ramRepo;

    @GetMapping("/manufacturer")
    public String manufacturer() {
        return "manufacturer";
    }

    @PostMapping("/manufacturer")
    public String addManufacturer(Manufacturer manufacturer, Model model) {
        if (manufacturerRepo.findFirstByTitle(manufacturer.getTitle()) != null) {
            model.addAttribute("message", "Данный производитель уже в базе данных.");
            return "manufacturer";
        }
        manufacturerRepo.save(manufacturer);
        return "manufacturer";
    }

    @GetMapping("/item")
    public String item() {
        return "item";
    }

    @PostMapping("/item")
    public String addItem(Manufacturer manufacturer, Model model) {
        if (manufacturerRepo.findFirstByTitle(manufacturer.getTitle()) != null) {
            model.addAttribute("message", "Данный производитель уже в базе данных.");
            return "manufacturer";
        }
        manufacturerRepo.save(manufacturer);
        return "manufacturer";
    }

    @GetMapping()
    public String items(Model model) {
        model.addAttribute("items", itemRepo.findAll());
        model.addAttribute("caseFormFactors", CaseFormFactor.values());
        model.addAttribute("caseWindows", CaseWindow.values());
        model.addAttribute("chipsets", Chipset.values());
        model.addAttribute("hardDriveFormFactors", HDFormFactor.values());
        model.addAttribute("hardDriveTypes", HDType.values());
        model.addAttribute("manufacturers", manufacturerRepo.findAll());
        model.addAttribute("motherboardFormFactors", MotherboardFormFactor.values());
        model.addAttribute("ramTechs", RamTech.values());
        model.addAttribute("sockets", Socket.values());
        model.addAttribute("coolingSystemTypes", Socket.values());
        model.addAttribute("itemTypes", ItemType.values());
        return "items";
    }

    @PostMapping("cpu")
    public @ResponseBody
    List<Cpu> filterCpu(String prices, String cores, String flows, String frequency, String socket) {
        Stream<Cpu> cpus = cpuRepo.findAll().stream();
        cpus = cpus.filter(cpu -> cpu.getItem().getPrice() >= Utility.toIntMin(prices) && cpu.getItem().getPrice() <= Utility.toIntMax(prices));
        cpus = cpus.filter(cpu -> cpu.getCores() >= Utility.toIntMin(cores) && cpu.getCores() <= Utility.toIntMax(cores));
        cpus = cpus.filter(cpu -> cpu.getFlows() >= Utility.toIntMin(flows) && cpu.getFlows() <= Utility.toIntMax(flows));
        cpus = cpus.filter(cpu -> cpu.getFrequency() >= Utility.toFloatMin(frequency) && cpu.getFrequency() <= Utility.toFloatMax(frequency));
        if (!socket.equals("-1"))
            cpus = cpus.filter(cpu -> cpu.getSocket() == Socket.valueOf(socket));
        return cpus.collect(Collectors.toList());
    }

    @PostMapping("cs")
    public @ResponseBody
    List<CoolingSystem> filterCS(String prices, String type, String diam, String count, String socket) {
        Stream<CoolingSystem> css = coolingSystemRepo.findAll().stream();
        css = css.filter(cs -> cs.getItem().getPrice() >= Utility.toIntMin(prices) && cs.getItem().getPrice() <= Utility.toIntMax(prices));
        css = css.filter(cs -> cs.getFanDiameter() >= Utility.toIntMin(diam) && cs.getFanDiameter() <= Utility.toIntMax(diam));
        css = css.filter(cs -> cs.getFanCount() >= Utility.toIntMin(count) && cs.getFanCount() <= Utility.toIntMax(count));
        if (!socket.equals("-1"))
            css = css.filter(cs -> cs.getSocket() == Socket.valueOf(socket));
        if (!type.equals("-1"))
            css = css.filter(cs -> cs.getType() == CoolingSystemType.valueOf(type));
        return css.collect(Collectors.toList());
    }

    @PostMapping("case")
    public @ResponseBody
    List<Case> filterCase(String prices, String caseFF, String mbFF, String window) {
        Stream<Case> cases = caseRepo.findAll().stream();
        cases = cases.filter(cs -> cs.getItem().getPrice() >= Utility.toIntMin(prices) && cs.getItem().getPrice() <= Utility.toIntMax(prices));
        if (!caseFF.equals("-1"))
            cases = cases.filter(cs -> cs.getCaseFormFactor() == CaseFormFactor.valueOf(caseFF));
        if (!mbFF.equals("-1"))
            cases = cases.filter(cs -> cs.getMotherboardFormFactor() == MotherboardFormFactor.valueOf(mbFF));
        if (!window.equals("-1"))
            cases = cases.filter(cs -> cs.getWindow() == CaseWindow.valueOf(window));
        return cases.collect(Collectors.toList());
    }

    @PostMapping("gpu")
    public @ResponseBody
    List<Gpu> filterGpu(String prices, String vram) {
        Stream<Gpu> gpus = gpuRepo.findAll().stream();
        gpus = gpus.filter(gpu -> gpu.getItem().getPrice() >= Utility.toIntMin(prices) && gpu.getItem().getPrice() <= Utility.toIntMax(prices));
        gpus = gpus.filter(gpu -> gpu.getVram() >= Utility.toFloatMin(vram) && gpu.getVram() <= Utility.toFloatMax(vram));
        return gpus.collect(Collectors.toList());
    }

    @PostMapping("hd")
    public @ResponseBody
    List<HardDrive> filterHD(String prices, String type, String hdFF, String capacity, String speed) {
        Stream<HardDrive> hds = hdRepo.findAll().stream();
        hds = hds.filter(hd -> hd.getItem().getPrice() >= Utility.toIntMin(prices) && hd.getItem().getPrice() <= Utility.toIntMax(prices));
        hds = hds.filter(hd -> hd.getCapacity() >= Utility.toIntMin(capacity) && hd.getCapacity() <= Utility.toIntMax(capacity));
        hds = hds.filter(hd -> hd.getRwSpeed() >= Utility.toIntMin(speed) && hd.getRwSpeed() <= Utility.toIntMax(speed));
        if (!hdFF.equals("-1"))
            hds = hds.filter(hd -> hd.getFormFactor() == HDFormFactor.valueOf(hdFF));
        if (!type.equals("-1"))
            hds = hds.filter(hd -> hd.getType() == HDType.valueOf(type));
        return hds.collect(Collectors.toList());
    }

    @PostMapping("mb")
    public @ResponseBody
    List<Motherboard> filterMB(String prices, String socket, String mbFF, String chipset, String ramCount, String ramTech) {
        Stream<Motherboard> mbs = motherboardRepo.findAll().stream();
        mbs = mbs.filter(mb -> mb.getItem().getPrice() >= Utility.toIntMin(prices) && mb.getItem().getPrice() <= Utility.toIntMax(prices));
        mbs = mbs.filter(mb -> mb.getNum_of_ram() >= Utility.toIntMin(ramCount) && mb.getNum_of_ram() <= Utility.toIntMax(ramCount));
        if (!socket.equals("-1"))
            mbs = mbs.filter(mb -> mb.getSocket() == Socket.valueOf(socket));
        if (!mbFF.equals("-1"))
            mbs = mbs.filter(mb -> mb.getFormFactor() == MotherboardFormFactor.valueOf(mbFF));
        if (!chipset.equals("-1"))
            mbs = mbs.filter(mb -> mb.getChipset() == Chipset.valueOf(chipset));
        if (!ramTech.equals("-1"))
            mbs = mbs.filter(mb -> mb.getRamTech() == RamTech.valueOf(ramTech));
        return mbs.collect(Collectors.toList());
    }

    @PostMapping("ps")
    public @ResponseBody
    List<PowerSupply> filterPS(String prices, String power, String sata, String pcie6, String pcie8) {
        Stream<PowerSupply> pss = powerSupplyRepo.findAll().stream();
        pss = pss.filter(ps -> ps.getItem().getPrice() >= Utility.toIntMin(prices) && ps.getItem().getPrice() <= Utility.toIntMax(prices));
        pss = pss.filter(ps -> ps.getPower() >= Utility.toIntMin(power) && ps.getPower() <= Utility.toIntMax(power));
        pss = pss.filter(ps -> ps.getSataCount() >= Utility.toIntMin(sata) && ps.getSataCount() <= Utility.toIntMax(sata));
        pss = pss.filter(ps -> ps.getPcie6Count() >= Utility.toIntMin(pcie6) && ps.getPcie6Count() <= Utility.toIntMax(pcie6));
        pss = pss.filter(ps -> ps.getPcie8Count() >= Utility.toIntMin(pcie8) && ps.getPcie8Count() <= Utility.toIntMax(pcie8));
        return pss.collect(Collectors.toList());
    }

    @PostMapping("ram")
    public @ResponseBody
    List<Ram> filterHD(String prices, String capacity, String frequency, String tech) {
        Stream<Ram> rams = ramRepo.findAll().stream();
        rams = rams.filter(ram -> ram.getItem().getPrice() >= Utility.toIntMin(prices) && ram.getItem().getPrice() <= Utility.toIntMax(prices));
        rams = rams.filter(ram -> ram.getCapacity() >= Utility.toIntMin(capacity) && ram.getCapacity() <= Utility.toIntMax(capacity));
        rams = rams.filter(ram -> ram.getFrequency() >= Utility.toIntMin(frequency) && ram.getFrequency() <= Utility.toIntMax(frequency));
        if (!tech.equals("-1"))
            rams = rams.filter(ram -> ram.getRamTech() == RamTech.valueOf(tech));
        return rams.collect(Collectors.toList());
    }
}
