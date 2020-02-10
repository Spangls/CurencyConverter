package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Rate;

import java.time.LocalDate;

public interface RateRepo extends CrudRepository<Rate, Long> {
    Rate findFirstByDate(LocalDate date);
    Rate findFirstByCurrency_CharCodeOrderByDateDesc(String charCode);
}
