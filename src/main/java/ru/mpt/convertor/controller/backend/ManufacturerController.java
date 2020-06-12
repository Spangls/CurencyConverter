package ru.mpt.convertor.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.convertor.model.Manufacturer;
import ru.mpt.convertor.repos.ManufacturerRepo;

@Controller
@RequestMapping("manufacturer")
@PreAuthorize("hasAuthority('EMPLOYEE')")
public class ManufacturerController {

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @GetMapping()
    public String getManufacturerList(Model model) {
        model.addAttribute("manufacturers", manufacturerRepo.findAll());
        return "manufacturer";
    }

    @GetMapping("new")
    public String addManufacturer() {
        return "manufacturerEdit";
    }

    @GetMapping("{manufacturer}")
    public String editManufacturer(@PathVariable Manufacturer manufacturer, Model model) {
        model.addAttribute(manufacturer);
        return "manufacturerEdit";
    }

    @PostMapping("add")
    public String addManufacturer(@RequestParam String title, Model model) {
        title = title.trim();
        if (manufacturerRepo.findFirstByTitle(title) != null) {
            model.addAttribute("message", "Данный производитель уже в базе данных.");
            return "manufacturerEdit";
        }
        manufacturerRepo.save(new Manufacturer(title));
        return "redirect:/manufacturer";
    }

    @PostMapping("edit")
    public String editManufacturer(@RequestParam String title, @RequestParam("manufacturerId") Manufacturer manufacturer, Model model) {
        title = title.trim();
        if (manufacturer.getTitle().equals(title))
            return "redirect:/manufacturer";
        if (manufacturerRepo.findFirstByTitle(title) != null) {
            model.addAttribute("message", "Данный производитель уже в базе данных.");
            return "manufacturerEdit";
        }
        manufacturer.setTitle(title);
        manufacturerRepo.save(manufacturer);
        return "redirect:/manufacturer";
    }

    @PostMapping("save")
    public String saveManufacturer(@RequestParam String title, @RequestParam(value = "manufacturerId", required = false) Manufacturer manufacturer, Model model) {
        title = title.trim();
        if (manufacturer == null) {
            if (manufacturerRepo.findFirstByTitle(title) != null) {
                model.addAttribute("message", "Данный производитель уже в базе данных.");
                return "manufacturerEdit";
            }
            manufacturerRepo.save(new Manufacturer(title));
            return "redirect:/manufacturer";
        }
        if (manufacturer.getTitle().equals(title))
            return "redirect:/manufacturer";
        if (manufacturerRepo.findFirstByTitle(title) != null) {
            model.addAttribute("message", "Данный производитель уже в базе данных.");
            return "manufacturerEdit";
        }
        manufacturer.setTitle(title);
        manufacturerRepo.save(manufacturer);
        return "redirect:/manufacturer";
    }

    @PostMapping("delete")
    public String deleteManufacturer(@RequestParam("manufacturerId") Manufacturer manufacturer, Model model) {
        manufacturerRepo.delete(manufacturer);
        model.addAttribute("manufacturers", manufacturerRepo.findAll());
        return "redirect:/manufacturer";
    }
}
