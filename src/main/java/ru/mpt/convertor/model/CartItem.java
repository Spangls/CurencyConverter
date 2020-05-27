package ru.mpt.convertor.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem implements Serializable {

    @EmbeddedId
    private CartItemId pk;

    @MapsId("cartId")
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cart cart;

    @MapsId("itemId")
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    public double calculateCost() {
        return item.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        pk.setCartId(cart.getId());
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        pk.setCartId(item.getId());
    }

    public CartItem(Cart cart, Item item, int quantity) {
        this.pk = new CartItemId(cart.getId(), item.getId());
        this.cart = cart;
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CartItem that = (CartItem) o;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return (pk != null ? pk.hashCode() : 0);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "pk=" + pk +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
