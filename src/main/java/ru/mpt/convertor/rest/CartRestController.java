package ru.mpt.convertor.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mpt.convertor.dto.CartDTO;
import ru.mpt.convertor.dto.CartItemDTO;
import ru.mpt.convertor.dto.assembler.CartDtoAssembler;
import ru.mpt.convertor.model.Cart;
import ru.mpt.convertor.service.CartService;

import javax.lang.model.UnknownEntityException;
import java.security.Principal;

@Controller
@RequestMapping(value = "/rest/cart")
public class CartRestController {
    private final CartService cartService;
    private final CartDtoAssembler cartDtoAssembler = new CartDtoAssembler();

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = FileType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public CartDTO getCart(Principal  principal){
        Cart cart = cartService.getCartOrCreate(principal.getName());
        return cartDtoAssembler.toModel(cart);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = FileType.APPLICATION_JSON_UTF8_VALUE,
            produces = FileType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public CartDTO addItem(Principal principal, @RequestBody CartItemDTO item) throws UnknownEntityException {
        String login = principal.getName();
        Cart cart = cartService.addToCart(login, item.getItemId(), item.getQuantity());
        return cartDtoAssembler.toModel(cart);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = FileType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public CartDTO clearCart(Principal principal) {
        Cart cart = cartService.clearCart(principal.getName());
        return cartDtoAssembler.toModel(cart);
    }
}
