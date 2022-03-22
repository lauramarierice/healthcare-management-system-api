package com.fsd.phase2.healthcaremanagementsystem.carts;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping(value = "/carts")
    public CartDTO addToUserCart(@RequestBody CartDTO cartDTO) {
        return cartService.createNewCart(cartDTO);
    }

    @GetMapping(value = "/carts/{id}")
    public CartDTO getCartByCartId(@PathVariable("id") Long cartId) {
        return cartService.getCartByCartId(cartId);
    }

    @GetMapping(value = "/users/{id}/carts")
    public CartDTO getCartByUserId(@PathVariable("id") Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping(value = "/users/{id}/carts")
    public void deleteCartByUserId(@PathVariable("id") Long userId) {
        cartService.deleteCartByUserId(userId);
    }
}