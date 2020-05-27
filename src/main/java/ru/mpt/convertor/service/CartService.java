package ru.mpt.convertor.service;

import ru.mpt.convertor.model.Cart;
import ru.mpt.convertor.model.CartItem;

import java.util.List;

public interface CartService {
    Cart getCartOrCreate(String userEmail);

    Cart addToCart(String userEmail, int itemId, int quantity);

    Cart addAllToCart(String userEmail, List<CartItem> itemsToAdd);

    Cart clearCart(String userEmail);
}
