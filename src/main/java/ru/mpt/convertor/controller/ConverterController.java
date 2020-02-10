package ru.mpt.convertor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mpt.convertor.Converter;

@Controller
public class ConverterController {
    @Autowired
    private Converter converter;

    @GetMapping("/")
    public String main() {
        return "redirect:/converter";
    }

    @GetMapping("/converter")
    public String converter(Model model) {
        model.addAttribute("currencyList", converter.getCurrencyList());
        return "converter";
    }

    @PostMapping("/converter")
    public @ResponseBody
    String convert(
            @RequestParam String sourceCurrency,
            @RequestParam String sourceAmount,
            @RequestParam String targetCurrency) {
        return converter.convert(sourceCurrency, Float.parseFloat(sourceAmount.replace(",", ".")), targetCurrency).toString().replace(",", ".");
    }
}
