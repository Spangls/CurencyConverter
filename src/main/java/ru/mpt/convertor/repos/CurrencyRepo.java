package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Currency;

import java.util.List;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {
    Currency findOneByNumCode(String numCode);
    Currency findFirstByCharCode(String charCode);
    List<Currency> findCurrenciesByNumCodeIn(List<String> numCodes);
    List<Currency> findAll();
}
