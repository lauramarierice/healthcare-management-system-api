package com.fsd.phase2.healthcaremanagementsystem.carts;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @CrossOrigin(origins = {"http://localhost:3000/cart", "http://localhost:3000/products", "http://localhost:3000"})
    @GetMapping(value = "/users/{id}/carts")
    public CartDTO getCartByUserId(@PathVariable("id") Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @CrossOrigin(origins = {"http://localhost:3000/cart", "http://localhost:3000/products", "http://localhost:3000"})
    @DeleteMapping(value = "/users/{id}/carts")
    public void deleteCartByUserId(@PathVariable("id") Long userId) {
        cartService.deleteCartByUserId(userId);
    }

    @CrossOrigin(origins = {"http://localhost:3000/cart", "http://localhost:3000/products", "http://localhost:3000"})
    @DeleteMapping(value = "/carts/{id}")
    public void deleteCartByCartId(@PathVariable("id") Long cartId) {
        cartService.deleteCartByCartId(cartId);
    }

    @CrossOrigin(origins = {"http://localhost:3000/cart", "http://localhost:3000/products", "http://localhost:3000"})
    @PutMapping(value = "/users/{userid}/carts-items", params = {"medicineid", "quantity"})
    public CartDTO updateCartItem(@PathVariable("userid") Long userId, @RequestParam("medicineid") Long medicineId, @RequestParam("quantity") Integer quantity) {
        return cartService.updateCartItemByUserId(userId, medicineId, quantity);
    }

    @CrossOrigin(origins = {"http://localhost:3000/cart", "http://localhost:3000/products", "http://localhost:3000"})
    @PutMapping(value = "/users/{userid}/increase/carts-items", params = {"medicineid"})
    public CartDTO increaseCartItemQuantity(@PathVariable("userid") Long userId, @RequestParam("medicineid") Long medicineId) {
        return cartService.increaseCartItemQuantityByUserId(userId, medicineId);
    }

    @CrossOrigin(origins = {"http://localhost:3000/cart", "http://localhost:3000/products", "http://localhost:3000"})
    @PutMapping(value = "/users/{userid}/decrease/carts-items", params = {"medicineid"})
    public CartDTO decreaseCartItemQuantity(@PathVariable("userid") Long userId, @RequestParam("medicineid") Long medicineId ) {
        return cartService.decreaseCartItemQuantityByUserId(userId, medicineId);
    }
}