package ru.mpt.convertor.controller.frontend;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.convertor.common.Utility;
import ru.mpt.convertor.model.*;
import ru.mpt.convertor.repos.*;
import ru.mpt.convertor.service.CountService;
import ru.mpt.convertor.service.FollowedItemService;
import ru.mpt.convertor.service.ItemService;
import ru.mpt.convertor.service.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("items")
public class ItemController {
    private final ManufacturerRepo manufacturerRepo;
    private final ItemService itemService;
    private final CpuRepo cpuRepo;
    private final CoolingSystemRepo coolingSystemRepo;
    private final CaseRepo caseRepo;
    private final GpuRepo gpuRepo;
    private final HDRepo hdRepo;
    private final MotherboardRepo motherboardRepo;
    private final PowerSupplyRepo powerSupplyRepo;
    private final RamRepo ramRepo;
    private final PriceRepo priceRepo;
    private final CountService countService;
    private final FollowedItemService  followedItemService;

    public ItemController(
            ManufacturerRepo manufacturerRepo,
            ItemService itemService,
            CpuRepo cpuRepo,
            CoolingSystemRepo coolingSystemRepo,
            CaseRepo caseRepo,
            GpuRepo gpuRepo,
            HDRepo hdRepo,
            MotherboardRepo motherboardRepo,
            PowerSupplyRepo powerSupplyRepo,
            RamRepo ramRepo,
            PriceRepo priceRepo,
            CountService countService,
            FollowedItemService followedItemService) {
        this.manufacturerRepo = manufacturerRepo;
        this.itemService = itemService;
        this.cpuRepo = cpuRepo;
        this.coolingSystemRepo = coolingSystemRepo;
        this.caseRepo = caseRepo;
        this.gpuRepo = gpuRepo;
        this.hdRepo = hdRepo;
        this.motherboardRepo = motherboardRepo;
        this.powerSupplyRepo = powerSupplyRepo;
        this.ramRepo = ramRepo;
        this.priceRepo = priceRepo;
        this.countService = countService;
        this.followedItemService = followedItemService;
    }

    @GetMapping()
    public String items(Principal principal, Model model) {
        if (principal == null || principal.getName() == null)
            return "redirect:/login";
        List<Item> items = itemService.findAll(principal.getName());
        model.addAttribute("items", items);
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

    @GetMapping("{itemId}")
    public String itemForm(@PathVariable Integer itemId, Model model) {
        model.addAttribute("item", itemService.getItem(itemId));
        return "item";
    }

    @GetMapping("/edit/{item}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String itemEditForm(@PathVariable Item item, Model model) {
        putEnums(model);
        model.addAttribute("item", item);
        switch (item.getType()) {
            case CPU:
                model.addAttribute("cpu", item.getCpu());
                break;
            case CASE:
                model.addAttribute("case", item.get_case());
                break;
            case CS:
                model.addAttribute("cs", item.getCoolingSystem());
                break;
            case GPU:
                model.addAttribute("gpu", item.getGpu());
                break;
            case HD:
                model.addAttribute("hd", item.getHardDrive());
                break;
            case MB:
                model.addAttribute("mb", item.getMotherboard());
                break;
            case PS:
                model.addAttribute("ps", item.getPowerSupply());
                break;
            case RAM:
                model.addAttribute("ram", item.getRam());
                break;
        }
        model.addAttribute("manufacturerList", manufacturerRepo.findAll());
        return "itemEdit";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String itemNewForm(Model model) {
        putEnums(model);
        model.addAttribute("manufacturerList", manufacturerRepo.findAll());
        return "itemEdit";
    }

    @PostMapping("/follow")
    public @ResponseBody boolean followItem(Principal principal, @RequestParam int itemId, @RequestParam boolean follow){
        String userEmail =  principal.getName();
        if (follow){
            return followedItemService.startItemFollow(userEmail, itemId) != null;
        } else {
            return followedItemService.finishItemFollow(userEmail, itemId) != null;
        }
    }

    //region save by types
    @PostMapping("save/cpu")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public @ResponseBody
    String saveItemCpu(
            @RequestParam(name = "itemId", required = false) Item item,
            @RequestParam String name,
            @RequestParam Manufacturer manufacturer,
            @RequestParam(name = "itemType") ItemType itemType,
            @RequestParam Float price,
            @RequestParam(name = "cpuId", required = false) Cpu cpu,
            @RequestParam Integer cores,
            @RequestParam Integer flows,
            @RequestParam Float frequency,
            @RequestParam Socket socket) {
        item = saveItem(item, name, manufacturer, itemType, price);
        if (cpu == null)
            cpu = new Cpu();
        cpu.setItem(item);
        cpu.setSocket(socket);
        cpu.setCores(cores);
        cpu.setFlows(flows);
        cpu.setFrequency(frequency);
        cpuRepo.save(cpu);
        return "http://localhost:8080/items";
    }


    @PostMapping("save/cs")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public @ResponseBody
    String saveItemCs(
            @RequestParam(name = "itemId", required = false) Item item,
            @RequestParam String name,
            @RequestParam Manufacturer manufacturer,
            @RequestParam(name = "itemType") ItemType itemType,
            @RequestParam Float price,
            @RequestParam(name = "csId", required = false) CoolingSystem cs,
            @RequestParam CoolingSystemType type,
            @RequestParam Integer fanCount,
            @RequestParam Integer fanDiam,
            @RequestParam Socket socket) {
        item = saveItem(item, name, manufacturer, itemType, price);
        if (cs == null)
            cs = new CoolingSystem();
        cs.setItem(item);
        cs.setType(type);
        cs.setFanCount(fanCount);
        cs.setFanDiameter(fanDiam);
        cs.setSocket(socket);
        coolingSystemRepo.save(cs);
        return "http://localhost:8080/items";
    }

    @PostMapping("save/case")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public @ResponseBody
    String saveItemCase(
            @RequestParam(name = "itemId", required = false) Item item,
            @RequestParam String name,
            @RequestParam Manufacturer manufacturer,
            @RequestParam(name = "itemType") ItemType itemType,
            @RequestParam Float price,
            @RequestParam(name = "caseId", required = false) Case _case,
            @RequestParam CaseFormFactor caseFormFactor,
            @RequestParam MotherboardFormFactor mbFormFactor,
            @RequestParam CaseWindow window) {
        item = saveItem(item, name, manufacturer, itemType, price);
        if (_case == null)
            _case = new Case();
        _case.setItem(item);
        _case.setCaseFormFactor(caseFormFactor);
        _case.setMotherboardFormFactor(mbFormFactor);
        _case.setWindow(window);
        caseRepo.save(_case);
        return "http://localhost:8080/items";
    }

    @PostMapping("save/gpu")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public @ResponseBody
    String saveItemGpu(
            @RequestParam(name = "itemId", required = false) Item item,
            @RequestParam String name,
            @RequestParam Manufacturer manufacturer,
            @RequestParam(name = "itemType") ItemType itemType,
            @RequestParam Float price,
            @RequestParam(name = "gpuId", required = false) Gpu gpu,
            @RequestParam Float vram) {
        item = saveItem(item, name, manufacturer, itemType, price);
        if (gpu == null)
            gpu = new Gpu();
        gpu.setItem(item);
        gpu.setVram(vram);
        gpuRepo.save(gpu);
        return "http://localhost:8080/items";
    }

    @PostMapping("save/hd")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public @ResponseBody
    String saveItemHd(
            @RequestParam(name = "itemId", required = false) Item item,
            @RequestParam String name,
            @RequestParam Manufacturer manufacturer,
            @RequestParam(name = "itemType") ItemType itemType,
            @RequestParam Float price,
            @RequestParam(name = "hdId", required = false) HardDrive hardDrive,
            @RequestParam HDType type,
            @RequestParam HDFormFactor formFactor,
            @RequestParam Integer capacity,
            @RequestParam Integer speed) {
        item = saveItem(item, name, manufacturer, itemType, price);
        if (hardDrive == null)
            hardDrive = new HardDrive();
        hardDrive.setItem(item);
        hardDrive.setType(type);
        hardDrive.setFormFactor(formFactor);
        hardDrive.setCapacity(capacity);
        hardDrive.setRwSpeed(speed);
        hdRepo.save(hardDrive);
        return "http://localhost:8080/items";
    }

    @PostMapping("save/mb")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public @ResponseBody
    String saveItemMb(
            @RequestParam(name = "itemId", required = false) Item item,
            @RequestParam String name,
            @RequestParam Manufacturer manufacturer,
            @RequestParam(name = "itemType") ItemType itemType,
            @RequestParam Float price,
            @RequestParam(name = "mbId", required = false) Motherboard motherboard,
            @RequestParam Socket socket,
            @RequestParam MotherboardFormFactor formFactor,
            @RequestParam Chipset chipset,
            @RequestParam RamTech ramTech,
            @RequestParam Integer ramSlotCount,
            @RequestParam String ports) {
        item = saveItem(item, name, manufacturer, itemType, price);
        if (motherboard == null)
            motherboard = new Motherboard();
        motherboard.setItem(item);
        motherboard.setSocket(socket);
        motherboard.setFormFactor(formFactor);
        motherboard.setChipset(chipset);
        motherboard.setRamTech(ramTech);
        motherboard.setNum_of_ram(ramSlotCount);
        motherboard.setPorts(ports);
        motherboardRepo.save(motherboard);
        return "http://localhost:8080/items";
    }

    @PostMapping("save/ps")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public @ResponseBody
    String saveItemPs(
            @RequestParam(name = "itemId", required = false) Item item,
            @RequestParam String name,
            @RequestParam Manufacturer manufacturer,
            @RequestParam(name = "itemType") ItemType itemType,
            @RequestParam Float price,
            @RequestParam(name = "psId", required = false) PowerSupply powerSupply,
            @RequestParam Integer power,
            @RequestParam Integer sata,
            @RequestParam Integer pcie6,
            @RequestParam Integer pcie8) {
        item = saveItem(item, name, manufacturer, itemType, price);
        if (powerSupply == null)
            powerSupply = new PowerSupply();
        powerSupply.setItem(item);
        powerSupply.setPower(power);
        powerSupply.setSataCount(sata);
        powerSupply.setPcie6Count(pcie6);
        powerSupply.setPcie8Count(pcie8);
        powerSupplyRepo.save(powerSupply);
        return "http://localhost:8080/items";
    }

    @PostMapping("save/ram")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public @ResponseBody
    String saveItemRam(
            @RequestParam(name = "itemId", required = false) Item item,
            @RequestParam String name,
            @RequestParam Manufacturer manufacturer,
            @RequestParam(name = "itemType") ItemType itemType,
            @RequestParam Float price,
            @RequestParam(name = "ramId", required = false) Ram ram,
            @RequestParam RamTech tech,
            @RequestParam Integer capacity,
            @RequestParam Integer frequency) {
        item = saveItem(item, name, manufacturer, itemType, price);
        if (ram == null)
            ram = new Ram();
        ram.setItem(item);
        ram.setRamTech(tech);
        ram.setCapacity(capacity);
        ram.setFrequency(frequency);
        ramRepo.save(ram);
        return "http://localhost:8080/items";
    }
    //endregion

    private Item saveItem(Item item, String name, Manufacturer manufacturer, ItemType itemType, Float price) {
        if (item == null)
            item = new Item(name, manufacturer, itemType);
        else {
            item.setName(name);
            item.setManufacturer(manufacturer);
            item.setType(itemType);
        }
        item = itemService.save(item);
        if (item.getPrice() != price) {
            priceRepo.save(new Price(item, price));
            item = itemService.findFirstById(item.getId());
        }
        return item;
    }

    @PostMapping("/count/add")
    public String addItemCount(@RequestParam(name = "itemId") Item item, @RequestParam Integer count) {
        if (item == null)
            return "redirect:/items";
        countService.save(new Count(item, count));
        followedItemService.sendSupplyMessage(item.getId());
        return "redirect:/items";
    }

    @GetMapping("all")
    public @ResponseBody
    Map<String, Object> getItems() {
        Map<String, Object> model = new HashMap<>();
        model.put("cpu", cpuRepo.findAll());
        model.put("motherboard", motherboardRepo.findAll());
        model.put("ram", ramRepo.findAll());
        model.put("hardDrive", hdRepo.findAll());
        model.put("case", caseRepo.findAll());
        return model;
    }

    @PostMapping("cpu")
    public @ResponseBody
    List<Cpu> filterCpu(Principal principal, String prices, String cores, String flows, String frequency, String socket) {
        Stream<Cpu> cpus = cpuRepo.findAll().stream();
        cpus = cpus.filter(cpu -> cpu.getItem().getPrice() >= Utility.toIntMin(prices) && cpu.getItem().getPrice() <= Utility.toIntMax(prices));
        cpus = cpus.filter(cpu -> cpu.getCores() >= Utility.toIntMin(cores) && cpu.getCores() <= Utility.toIntMax(cores));
        cpus = cpus.filter(cpu -> cpu.getFlows() >= Utility.toIntMin(flows) && cpu.getFlows() <= Utility.toIntMax(flows));
        cpus = cpus.filter(cpu -> cpu.getFrequency() >= Utility.toFloatMin(frequency) && cpu.getFrequency() <= Utility.toFloatMax(frequency));
        if (!socket.equals("-1"))
            cpus = cpus.filter(cpu -> cpu.getSocket() == Socket.valueOf(socket));
        List<Cpu> finalized = cpus.collect(Collectors.toList());
        finalized.forEach(cpu -> itemService.checkFollow(cpu.getItem(), principal.getName()));
        return finalized;
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
        List<CoolingSystem> finalized = css.collect(Collectors.toList());
        finalized.forEach(ram -> itemService.calculateCount(ram.getItem()));
        return finalized;
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
        List<Case> finalized = cases.collect(Collectors.toList());
        finalized.forEach(ram -> itemService.calculateCount(ram.getItem()));
        return finalized;
    }

    @PostMapping("gpu")
    public @ResponseBody
    List<Gpu> filterGpu(String prices, String vram) {
        Stream<Gpu> gpus = gpuRepo.findAll().stream();
        gpus = gpus.filter(gpu -> gpu.getItem().getPrice() >= Utility.toIntMin(prices) && gpu.getItem().getPrice() <= Utility.toIntMax(prices));
        gpus = gpus.filter(gpu -> gpu.getVram() >= Utility.toFloatMin(vram) && gpu.getVram() <= Utility.toFloatMax(vram));
        List<Gpu> finalized = gpus.collect(Collectors.toList());
        finalized.forEach(ram -> itemService.calculateCount(ram.getItem()));
        return finalized;
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
        List<HardDrive> finalized = hds.collect(Collectors.toList());
        finalized.forEach(ram -> itemService.calculateCount(ram.getItem()));
        return finalized;
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
        List<Motherboard> finalized = mbs.collect(Collectors.toList());
        finalized.forEach(ram -> itemService.calculateCount(ram.getItem()));
        return finalized;
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
        List<PowerSupply> finalized = pss.collect(Collectors.toList());
        finalized.forEach(ram -> itemService.calculateCount(ram.getItem()));
        return finalized;
    }

    @PostMapping("ram")
    public @ResponseBody
    List<Ram> filterRam(String prices, String capacity, String frequency, String tech) {
        Stream<Ram> rams = ramRepo.findAll().stream();
        rams = rams.filter(ram -> ram.getItem().getPrice() >= Utility.toIntMin(prices) && ram.getItem().getPrice() <= Utility.toIntMax(prices));
        rams = rams.filter(ram -> ram.getCapacity() >= Utility.toIntMin(capacity) && ram.getCapacity() <= Utility.toIntMax(capacity));
        rams = rams.filter(ram -> ram.getFrequency() >= Utility.toIntMin(frequency) && ram.getFrequency() <= Utility.toIntMax(frequency));
        if (!tech.equals("-1"))
            rams = rams.filter(ram -> ram.getRamTech() == RamTech.valueOf(tech));
        List<Ram> finalized = rams.collect(Collectors.toList());
        finalized.forEach(ram -> itemService.calculateCount(ram.getItem()));
        return finalized;
    }

    private Model putEnums(Model model) {
        model.addAttribute("motherboardFormFactorList", MotherboardFormFactor.values());
        model.addAttribute("ramTechList", RamTech.values());
        model.addAttribute("socketList", Socket.values());
        model.addAttribute("coolingSystemTypeList", Socket.values());
        model.addAttribute("caseFormFactorList", CaseFormFactor.values());
        model.addAttribute("caseWindowList", CaseWindow.values());
        model.addAttribute("chipsetList", Chipset.values());
        model.addAttribute("hardDriveFormFactorList", HDFormFactor.values());
        model.addAttribute("hardDriveTypeList", HDType.values());
        model.addAttribute("itemTypeList", ItemType.values());
        return model;
    }
}
