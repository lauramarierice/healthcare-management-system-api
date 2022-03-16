package com.fsd.phase2.healthcaremanagementsystem.carts.cart_items;

import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
                .orElseThrow(EntityNotFoundException::new));
    }

    public CartItemDTO updateCartItem(Long cartId, Long medicineId, Integer quantity) {
        CartItemEntity cartItemEntity = cartItemRepository.findByCartIdAndMedicineId(cartId, medicineId)
                .orElseThrow(EntityNotFoundException::new);

        cartItemEntity.setQuantity(quantity);
        cartItemRepository.save(cartItemEntity);

        return cartItemMapper.map(cartItemEntity);
    }

    public List<CartItemDTO> findCartItemsByCartId(Long cartId) {
        return cartItemMapper.map(cartItemRepository.findByCartId(cartId));
    }

    @Transactional
    public void deleteCartItemByCartIdAndMedicineId(Long cartId, Long medicineId) {
        cartItemRepository.deleteByCartIdAndMedicineId(cartId, medicineId);
    }
}
