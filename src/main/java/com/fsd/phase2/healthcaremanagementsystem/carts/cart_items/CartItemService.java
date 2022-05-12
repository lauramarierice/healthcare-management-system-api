package com.fsd.phase2.healthcaremanagementsystem.carts.cart_items;

import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemMapper cartItemMapper;

    private final CartItemRepository cartItemRepository;

    public CartItemDTO createCartItem(CartItemEntity cartItemEntity) {
        return cartItemMapper.map(cartItemRepository.save(cartItemEntity));
    }

    public CartItemDTO getCartItemByCartIdAndMedicineId(Long cartId, Long medicineId) {
        return cartItemMapper.map(cartItemRepository.findByCartIdAndMedicineId(cartId, medicineId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found")));
    }

    public CartItemDTO updateCartItem(Long cartId, Long medicineId, Integer quantity) {
        CartItemEntity cartItemEntity = cartItemRepository.findByCartIdAndMedicineId(cartId, medicineId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        cartItemEntity.setQuantity(quantity);
        cartItemRepository.save(cartItemEntity);

        return cartItemMapper.map(cartItemEntity);
    }

    public void updateCartItemByUserId(Long userId, Long medicineId, Integer quantity) {
        CartItemDTO cartItem = cartItemMapper.map(cartItemRepository.findByUserIdAndMedicineId(userId, medicineId)
                .orElse(null));

        if (Objects.isNull(cartItem)) {
            CartItemEntity newCartItem = new CartItemEntity();
            Long cartId = cartItemRepository.findCartIdByUserId(userId).orElse(null);

            newCartItem.setMedicineId(medicineId);
            newCartItem.setQuantity(quantity);
            newCartItem.setCartId(cartId);
            cartItemRepository.save(newCartItem);
        } else {
            Integer newQuantity = cartItem.getQuantity() + 1;
            cartItem.setQuantity(newQuantity);
            cartItemRepository.save(cartItemMapper.map(cartItem));
        }
    }

    public void increaseCartItemQuantityByUserId(Long userId, Long medicineId) {
        CartItemDTO cartItem = cartItemMapper.map(cartItemRepository.findByUserIdAndMedicineId(userId, medicineId).orElse(null));

        Integer newQuantity = cartItem.getQuantity() + 1;
        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItemMapper.map(cartItem));
    }

    public void decreaseCartItemQuantityByUserId(Long userId, Long medicineId) {
        CartItemDTO cartItem = cartItemMapper.map(cartItemRepository.findByUserIdAndMedicineId(userId, medicineId).orElse(null));

        Integer newQuantity = cartItem.getQuantity() - 1;
        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItemMapper.map(cartItem));
    }

    public List<CartItemDTO> findCartItemsByCartId(Long cartId) {
        return cartItemMapper.map(cartItemRepository.findByCartId(cartId));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCartItemByUserIdAndMedicineId(Long userId, Long medicineId) {
        Long cartId = cartItemRepository.findCartIdByUserId(userId).orElse(null);
        cartItemRepository.deleteByCartIdAndMedicineId(cartId, medicineId);
    }
}
