package com.fsd.phase2.healthcaremanagementsystem.carts;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO map(CartEntity source);

    CartEntity map(CartDTO source);

    List<CartDTO> map(List<CartEntity> source);
}
