package com.fsd.phase2.healthcaremanagementsystem.orders.order_items;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDTO map(OrderItemEntity source);

    OrderItemEntity map(OrderItemDTO source);

    List<OrderItemDTO> map(List<OrderItemEntity> source);
}
