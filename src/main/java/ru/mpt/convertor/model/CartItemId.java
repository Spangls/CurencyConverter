package ru.mpt.convertor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartItemId implements Serializable {
    private int cartId;
    private int itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemId that = (CartItemId) o;
        return cartId == that.cartId &&
                itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, itemId);
    }

    @Override
    public String toString() {
        return "CartItemId{" +
                "cartId=" + cartId +
                ", itemId=" + itemId +
                '}';
    }
}
