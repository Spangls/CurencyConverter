package ru.mpt.convertor.controller.frontend;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mpt.convertor.dto.CartDTO;
import ru.mpt.convertor.dto.CartItemDTO;
import ru.mpt.convertor.dto.assembler.CartDtoAssembler;
import ru.mpt.convertor.model.Cart;
import ru.mpt.convertor.model.CartItem;
import ru.mpt.convertor.model.Item;
import ru.mpt.convertor.service.CartService;
import ru.mpt.convertor.service.ItemService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
@Secured({"ROLE_USER"})
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;
    private final CartDtoAssembler cartDtoAssembler = new CartDtoAssembler();

    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCart(Model model, Principal principal) {
        Cart cart = cartService.getCartOrCreate(principal.getName());
        model.addAttribute("cart", cartDtoAssembler.toModel(cart));
        model.addAttribute("itemsById", collectItemsMap(cart));
        return "cart";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public String clearCart(Model model, Principal principal, @ModelAttribute(value = "cart") CartDTO cartDTO) {
        Cart clearedCart = cartService.clearCart(principal.getName());
        model.addAttribute("cart", cartDtoAssembler.toModel(clearedCart));
        return "redirect:/cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateCartByForm(
            Model model, Principal principal,
            @Valid @ModelAttribute("cartItem") CartItemDTO cartItemDto, BindingResult bindingResult,
            @ModelAttribute(value = "cart") CartDTO cartDTO
    ) {
        if (bindingResult.hasErrors())
            return "cart";
        Cart updatedCart = updateCart(principal, cartItemDto);
        model.addAttribute("cart", cartDtoAssembler.toModel(updatedCart));
        return "redirect:/cart";
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CartDTO updateCartByAjax(
            Model model, Principal principal,
            @Valid @RequestBody CartItemDTO cartItemDto, BindingResult bindingResult,
            @ModelAttribute(value = "cart") CartDTO cartDTO
    ) {
        if (bindingResult.hasErrors())
            return cartDTO;

        Cart updatedCart = updateCart(principal, cartItemDto);
        return cartDtoAssembler.toModel(updatedCart);
    }

    private Map<String, Item> collectItemsMap(Cart cart) {
        return cart.getCartItems().stream()
                .map(CartItem::getItem)
                .collect(Collectors.toMap(item -> item.getId().toString(), i -> i));
    }

    private Cart updateCart(Principal principal, CartItemDTO cartItem) {
        return cartService.addToCart(principal.getName(), cartItem.getItemId(), cartItem.getQuantity());
    }

    private boolean isAuthorized(Principal principal) {
        return principal != null;
    }
}
