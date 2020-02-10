package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Currency;
import ru.mpt.convertor.model.History;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepo extends CrudRepository<History, Long> {
    List<History> findAll();
    List<History> findAllByDate(LocalDate date);
    List<History> findAllByDateAndSourceCurrencyAndTargetCurrency(LocalDate date, Currency sourceCurrency, Currency targetCurrency);
}
