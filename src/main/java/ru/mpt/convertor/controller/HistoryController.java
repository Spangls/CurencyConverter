package ru.mpt.convertor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {

//    @Autowired
//    private Converter converter;
//    @Autowired
//    private HistoryRepo historyRepo;
//    @Autowired
//    private CurrencyRepo currencyRepo;
//
//    @GetMapping
//    public String history(
//            @RequestParam(required = false) String sourceCurrency,
//            @RequestParam(required = false) String targetCurrency,
//            @RequestParam(required = false) String date,
//            Model model) {
//        model.addAttribute("currencyList", converter.getCurrencyList());
//
//        List<History> history;
//        if (sourceCurrency == null || targetCurrency == null)
//            history = historyRepo.findAllByDate(LocalDate.now());
//        else {
//            Currency sc = currencyRepo.findFirstByCharCode(sourceCurrency);
//            Currency tc = currencyRepo.findFirstByCharCode(targetCurrency);
//            history = historyRepo.findAllByDateAndSourceCurrencyAndTargetCurrency(date == null ? LocalDate.now() : LocalDate.parse(date), sc, tc);
//        }
//        model.addAttribute("historyList", history);
//        if (date == null || date.isEmpty())
//            model.addAttribute("date", LocalDate.now());
//        else
//            model.addAttribute("date", LocalDate.parse(date));
//        if (sourceCurrency != null)
//            model.addAttribute("sourceCurrency", sourceCurrency);
//        if (targetCurrency != null)
//            model.addAttribute("targetCurrency", targetCurrency);
//        return "history";
//    }

}
