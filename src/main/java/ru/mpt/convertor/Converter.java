package ru.mpt.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.mpt.convertor.model.Currency;
import ru.mpt.convertor.model.History;
import ru.mpt.convertor.model.Rate;
import ru.mpt.convertor.repos.CurrencyRepo;
import ru.mpt.convertor.repos.HistoryRepo;
import ru.mpt.convertor.repos.RateRepo;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Component
public class Converter {
    @Autowired
    private CurrencyRepo currencyRepo;
    @Autowired
    private RateRepo rateRepo;
    @Autowired
    private HistoryRepo historyRepo;

    private final static String RATE_URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    private List<Currency> currencyList;

    public Converter() {
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    @Scheduled(fixedRate = 86_400_000)
    @PostConstruct
    public void updateRate(){
        try {
            if (rateRepo.findFirstByDate(LocalDate.now()) == null){
                CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                URLConnection connection = new URL(RATE_URL).openConnection();
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());
                NodeList currencyList = document.getDocumentElement().getElementsByTagName("Valute");
                for (int i = 0; i < currencyList.getLength(); i++) {
                    Element valute = (Element) currencyList.item(i);
                    String numCode = valute.getElementsByTagName("NumCode").item(0).getTextContent();
                    Currency currency = currencyRepo.findOneByNumCode(numCode);
                    if(currency == null){
                        currency = currencyRepo.save(new Currency(
                                valute.getElementsByTagName("Name").item(0).getTextContent(),
                                valute.getElementsByTagName("CharCode").item(0).getTextContent(),
                                valute.getElementsByTagName("NumCode").item(0).getTextContent(),
                                Integer.parseInt(valute.getElementsByTagName("Nominal").item(0).getTextContent())
                        ));
                    }
                    rateRepo.save(new Rate(currency, valute.getElementsByTagName("Value").item(0).getTextContent()));
                }
                if (currencyRepo.findFirstByCharCode("RUB") == null)
                    currencyRepo.save(new Currency("Российский рубль", "RUB", "000", 1));
                rateRepo.save(new Rate(currencyRepo.findFirstByCharCode("RUB"), 1f));
            }
            this.currencyList = currencyRepo.findAll();
        }
        catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }

    public Float convert(String sourceCurrencyCharCode, float sourceAmount, String targetCurrencyCharCode){
        Rate sourceRate = rateRepo.findFirstByCurrency_CharCodeOrderByDateDesc(sourceCurrencyCharCode);
        Rate targetRate = rateRepo.findFirstByCurrency_CharCodeOrderByDateDesc(targetCurrencyCharCode);
        Currency sourceCurrency = sourceRate.getCurrency();
        Currency targetCurrency = targetRate.getCurrency();
        float targetAmount = (sourceAmount*targetCurrency.getNominal()*sourceRate.getValue())/(sourceCurrency.getNominal()*targetRate.getValue());
        historyRepo.save(new History(sourceCurrency, sourceAmount, targetCurrency, targetAmount));
        return targetAmount;
    }
}
