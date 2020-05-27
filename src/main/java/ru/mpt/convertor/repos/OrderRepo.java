package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Order;
import ru.mpt.convertor.model.StatusOrder;
import ru.mpt.convertor.model.User;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order, Integer>, JpaRepository<Order, Integer> {
    List<Order> findByUserOrderByDateDesc(User user);
    List<Order> findAllByStatus(StatusOrder status);
}
