package ru.mpt.convertor.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO extends RepresentationModel<CartDTO> {

    private String user;
    private List<CartItemDTO> cartItems = Collections.emptyList();
    private int itemsCount;
    private double totalCost;
}
