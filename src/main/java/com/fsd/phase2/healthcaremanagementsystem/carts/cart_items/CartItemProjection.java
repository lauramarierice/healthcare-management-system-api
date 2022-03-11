package com.fsd.phase2.healthcaremanagementsystem.carts.cart_items;

public interface CartItemProjection {

    Long getCartId();

    Long getCartItemId();

    Long getMedicineId();

    Integer getQuantity();

    String getMedicineName();

    Double getPrice();
}
