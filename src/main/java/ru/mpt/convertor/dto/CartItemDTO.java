package ru.mpt.convertor.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO extends RepresentationModel<CartItemDTO> {
    private int itemId;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemDTO)) return false;
        if (!super.equals(o)) return false;
        CartItemDTO that = (CartItemDTO) o;
        return itemId == that.itemId &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itemId, quantity);
    }
}
