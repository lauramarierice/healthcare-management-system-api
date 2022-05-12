package com.fsd.phase2.healthcaremanagementsystem.carts;

import com.fsd.phase2.healthcaremanagementsystem.carts.cart_items.CartItemDTO;
import com.fsd.phase2.healthcaremanagementsystem.carts.cart_items.CartItemService;
import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

        addCartItems(cartDTO);

        return cartDTO;
    }

    public CartDTO getCartByUserId(Long userId) {
        CartDTO cartDTO = cartMapper.map(cartRepository.getByUserId(userId)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Cart not found.");
                }));

        addCartItems(cartDTO);

        return cartDTO;
    }

    public CartDTO createNewCart(CartDTO cartDTO) {
        return cartMapper.map(cartRepository.save(cartMapper.map(cartDTO)));
    }

    public CartDTO createNewCartByUserId(Long userId, Long medicineId,Integer quantity) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(userId);
        cartRepository.save(cartEntity);

        cartItemService.updateCartItemByUserId(userId, medicineId, quantity);

        return this.getCartByUserId(userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCartByUserId(Long patientId) {
        cartRepository.deleteByUserId(patientId);
    }

    public void deleteCartByCartId(Long cartId) {
        cartRepository.deleteByCartId(cartId);
    }

    private void addCartItems(CartDTO cartDTO) {
        List<CartItemDTO> cartItems = cartItemService.findCartItemsByCartId(cartDTO.getCartId());
        cartDTO.setCartItems(cartItems);

        Double totalCost = 0.0;
        for (CartItemDTO cartItem : cartItems) {
            totalCost += (cartItem.getPrice() * cartItem.getQuantity());
        }

        cartDTO.setCartCost(totalCost);
    }

    public CartDTO updateCartItemByUserId(Long userId, Long medicineId, Integer quantity) {

        CartDTO cartDTO = cartMapper.map(cartRepository.getByUserId(userId).orElse(null));

        if(Objects.isNull(cartDTO)) {
            this.createNewCartByUserId(userId, medicineId, quantity);
        } else {
            cartItemService.updateCartItemByUserId(userId, medicineId, quantity);
        }
        return this.getCartByUserId(userId);
    }

    public CartDTO increaseCartItemQuantityByUserId(Long userId, Long medicineId) {
        cartItemService.increaseCartItemQuantityByUserId(userId, medicineId);
        return this.getCartByUserId(userId);
    }

    public CartDTO decreaseCartItemQuantityByUserId(Long userId, Long medicineId) {
        cartItemService.decreaseCartItemQuantityByUserId(userId, medicineId);
        return this.getCartByUserId(userId);
    }
}
