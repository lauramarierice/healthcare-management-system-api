package com.fsd.phase2.healthcaremanagementsystem.orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "orderDate", source = "orderDate")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "orderStatus", source = "orderStatus")
    OrderDTO map(OrderEntity source);

    OrderEntity mapEntity(OrderDTO source);

    List<OrderDTO> mapList(List<OrderEntity> source);

    List<OrderDTO> mapProjectionList(List<OrderProjection> source);
}
