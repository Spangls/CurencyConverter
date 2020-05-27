package ru.mpt.convertor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpt.convertor.model.Cart;
import ru.mpt.convertor.model.CartItem;
import ru.mpt.convertor.model.Item;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.repos.CartRepo;
import ru.mpt.convertor.service.CartService;
import ru.mpt.convertor.service.ItemService;
import ru.mpt.convertor.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final UserService userService;
    private final ItemService itemService;

    public CartServiceImpl(CartRepo cartRepo, UserService userService, ItemService itemService) {
        this.cartRepo = cartRepo;
        this.userService = userService;
        this.itemService = itemService;
    }

    private Cart createCart(User user) {
        return cartRepo.save(new Cart(user));
    }

    @Transactional
    @Override
    public Cart getCartOrCreate(String userEmail) {
        User user = userService.findByEmail(userEmail);
        Optional<Cart> cartOptional = cartRepo.findById(user.getId());
        return cartOptional.orElseGet(() -> createCart(user));
    }

    @Transactional
    @Override
    public Cart addToCart(String userEmail, int itemId, int quantity) {
        Cart cart = getCartOrCreate(userEmail);
        Item item = itemService.getItem(itemId);
        if (item.getCount() > 0) {
            cart.update(item, quantity);
            return cartRepo.save(cart);
        } else {
            return cart;
        }
    }

    @Transactional
    @Override
    public Cart addAllToCart(String userEmail, List<CartItem> itemsToAdd) {
        Cart cart = getCartOrCreate(userEmail);
        boolean updated = false;
        for (CartItem cartItem : itemsToAdd) {
            Optional<Item> item = itemService.findById(cartItem.getItem().getId());
            if (item.isPresent() && item.get().getCount() > 0) {
                cart.update(item.get(), cartItem.getQuantity());
                updated = true;
            }
        }
        return updated ? cartRepo.save(cart) : cart;
    }

    @Transactional
    @Override
    public Cart clearCart(String userEmail) {
        Cart cart = getCartOrCreate(userEmail);
        cart.clear();
        cart.setItemsCost(0);
        cart.setItemsCount(0);
        return cartRepo.save(cart);
    }
}
