package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.OrderedService;

public interface ServiceRepo extends CrudRepository<OrderedService, Integer>, JpaRepository<OrderedService, Integer> {
}
