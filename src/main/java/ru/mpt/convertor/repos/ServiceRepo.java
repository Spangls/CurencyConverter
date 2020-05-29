package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.OrderedService;

import java.util.List;

public interface ServiceRepo extends CrudRepository<OrderedService, Integer>, JpaRepository<OrderedService, Integer> {
    List<OrderedService> findAllByActive(Boolean active);
}
