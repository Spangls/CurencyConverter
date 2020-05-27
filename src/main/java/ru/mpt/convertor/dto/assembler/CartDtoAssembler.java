package ru.mpt.convertor.dto.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import ru.mpt.convertor.dto.CartDTO;
import ru.mpt.convertor.dto.CartItemDTO;
import ru.mpt.convertor.model.Cart;
import ru.mpt.convertor.model.CartItem;
import ru.mpt.convertor.rest.CartRestController;

import java.util.List;
import java.util.stream.Collectors;

public class CartDtoAssembler extends RepresentationModelAssemblerSupport<Cart, CartDTO> {

    public CartDtoAssembler(){
        super(CartRestController.class, CartDTO.class);
    }

    @Override
    public CartDTO toModel(Cart cart){
        CartDTO dto = instantiateModel(cart);
        dto.setUser(cart.getUser().getEmail());
        dto.setItemsCount(cart.getItemsCount());
        dto.setTotalCost(cart.getItemsCost());
        List<CartItemDTO> cartItemDto = cart.getCartItems().stream()
                .map(this::toCartItemDto)
                .collect(Collectors.toList());
        dto.setCartItems(cartItemDto);
        return dto;
    }

    public CartItemDTO toCartItemDto(CartItem cartItem){
        CartItemDTO dto = new CartItemDTO();
        dto.setItemId(cartItem.getItem().getId());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }
}
