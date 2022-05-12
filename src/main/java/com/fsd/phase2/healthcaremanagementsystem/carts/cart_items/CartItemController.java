package com.fsd.phase2.healthcaremanagementsystem.carts.cart_items;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/carts/{id}/items")
    public List<CartItemDTO> findCartItemsByCartId(@PathVariable("id") Long cartId) {
        return cartItemService.findCartItemsByCartId(cartId);
    }

    @GetMapping(value = "/carts/{id}/items", params = {"medicineid"})
    public CartItemDTO getCartItemByCartIdAndMedicineId(@PathVariable("id") Long cartId, @RequestParam("medicineid") Long medicineId) {
        return cartItemService.getCartItemByCartIdAndMedicineId(cartId, medicineId);
    }

    @DeleteMapping(value = "/users/{id}/cart-items", params = {"medicineid"})
    public void deleteMedicineFromCart(@PathVariable("id") Long userId, @RequestParam("medicineid") Long medicineId) {
        cartItemService.deleteCartItemByUserIdAndMedicineId(userId, medicineId);
    }

}