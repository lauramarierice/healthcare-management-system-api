package com.fsd.phase2.healthcaremanagementsystem.orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "orderStatus", source = "orderStatus")
    OrderDTO map(OrderEntity source);

    OrderEntity map(OrderDTO source);

    List<OrderDTO> map(List<OrderEntity> source);
}
