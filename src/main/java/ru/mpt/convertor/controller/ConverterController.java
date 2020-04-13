package ru.mpt.convertor.controller;

import org.springframework.stereotype.Controller;

@Controller
public class ConverterController {
//    @Autowired
//    private Converter converter;
//
//    @GetMapping("/")
//    public String main() {
//        return "redirect:/converter";
//    }
//
//    @GetMapping("/converter")
//    public String converter(Model model) {
//        model.addAttribute("currencyList", converter.getCurrencyList());
//        return "converter";
//    }
//
//    @PostMapping("/converter")
//    public @ResponseBody
//    String convert(
//            @RequestParam String sourceCurrency,
//            @RequestParam String sourceAmount,
//            @RequestParam String targetCurrency) {
//        return converter.convert(sourceCurrency, Float.parseFloat(sourceAmount.replace(",", ".")), targetCurrency).toString().replace(",", ".");
//    }
}
