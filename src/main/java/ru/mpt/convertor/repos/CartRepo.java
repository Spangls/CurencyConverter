package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Cart;

public interface CartRepo extends CrudRepository<Cart, Integer>, JpaRepository<Cart, Integer> {
}
