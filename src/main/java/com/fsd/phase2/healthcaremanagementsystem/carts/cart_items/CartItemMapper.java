package com.fsd.phase2.healthcaremanagementsystem.carts.cart_items;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "cartId", source = "cartId")
    @Mapping(target = "cartItemId", source = "cartItemId")
    @Mapping(target = "medicineId", source = "medicineId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "medicineName", source = "medicineName")
    @Mapping(target = "price", source = "price")
    CartItemDTO map(CartItemProjection source);

    CartItemDTO map(CartItemEntity source);

    CartItemEntity map(CartItemDTO source);

    List<CartItemDTO> map(List<CartItemProjection> source);
}
