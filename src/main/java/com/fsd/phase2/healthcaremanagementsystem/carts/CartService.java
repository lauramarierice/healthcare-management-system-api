package com.fsd.phase2.healthcaremanagementsystem.carts;

import com.fsd.phase2.healthcaremanagementsystem.carts.cart_items.CartItemDTO;
import com.fsd.phase2.healthcaremanagementsystem.carts.cart_items.CartItemService;
import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    private final CartRepository cartRepository;

    private final CartItemService cartItemService;

    public CartDTO getCartByCartId(Long patientId) {
        CartDTO cartDTO = cartMapper.map(cartRepository.getByCartId(patientId)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Cart not found.");
                }));

        List<CartItemDTO> cartItems = cartItemService.findCartItemsByCartId(cartDTO.getCartId());
        cartDTO.setCartItems(cartItems);

        Double totalCost = 0.0;
        for(CartItemDTO cartItem :cartItems) {
            totalCost += (cartItem.getPrice() * cartItem.getQuantity());
        }

        cartDTO.setCartCost(totalCost);

        return cartDTO;
    }

    public CartDTO getCartByUserId(Long userId) {
        CartDTO cartDTO = cartMapper.map(cartRepository.getByUserId(userId)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Cart not found.");
                }));

        List<CartItemDTO> cartItems = cartItemService.findCartItemsByCartId(cartDTO.getCartId());
        cartDTO.setCartItems(cartItems);

        Double totalCost = 0.0;
        for(CartItemDTO cartItem :cartItems) {
            totalCost += (cartItem.getPrice() * cartItem.getQuantity());
        }

        cartDTO.setCartCost(totalCost);

        return cartDTO;
    }

    public CartDTO createNewCart(CartEntity cartEntity) {
        return cartMapper.map(cartRepository.save(cartEntity));
    }

    public void deleteCartByUserId(Long patientId) {
        cartRepository.deleteByUserId(patientId);
    }
}
