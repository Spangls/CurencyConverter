package ru.mpt.convertor.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
    private int id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = CartItem.class, mappedBy = "cart")
    private Set<CartItem> cartItems = new HashSet<>(0);

    @Column(name = "total_cost")
    private Integer itemsCost;
    @Column(name = "total_items")
    private Integer itemsCount;

    public Cart() {
        this(null);
    }

    public Cart(User user) {
        this.user = user;
        itemsCost = calculateItemsCost();
        itemsCount = calculateItemsCount();
    }

    public Set<CartItem> getCartItems() {
        return Collections.unmodifiableSet(cartItems);
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getItemsCost() {
        return itemsCost;
    }

    public void setItemsCost(Integer itemsCost) {
        this.itemsCost = itemsCost;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public int calculateItemsCost() {
        return Double.valueOf(cartItems.stream().mapToDouble(CartItem::calculateCost).sum()).intValue();
    }

    public int calculateItemsCount() {
        return cartItems.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public CartItem update(Item item, int newQuantity) {
        if (item == null)
            return null;
        CartItem updatedItem = null;
        if (newQuantity > 0) {
            CartItem existingItem = findItem(item.getId());
            if (existingItem == null) {
                CartItem newItem = new CartItem(this, item, newQuantity);
                cartItems.add(newItem);
                updatedItem = newItem;
            } else {
                existingItem.setQuantity(newQuantity);
                updatedItem = existingItem;
            }
        } else {
            removeItem(item.getId());
        }
        itemsCost = calculateItemsCost();
        itemsCount = calculateItemsCount();
        return updatedItem;
    }

    private void removeItem(int itemId) {
        cartItems.removeIf(item -> item.getItem().getId() == itemId);
    }

    public void clear() {
        cartItems.clear();
    }

    private CartItem findItem(int itemId) {
        for (CartItem existingItem : cartItems) {
            if (existingItem.getItem().getId() == itemId)
                return existingItem;
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(cartItems, cart.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartItems);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", cartItems=" + cartItems +
                ", itemsCost=" + itemsCost +
                ", itemsCount=" + itemsCount +
                '}';
    }
}
