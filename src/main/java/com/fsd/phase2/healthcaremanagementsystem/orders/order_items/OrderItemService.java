package com.fsd.phase2.healthcaremanagementsystem.orders.order_items;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService {

    private final OrderItemMapper orderItemMapper;

    private final OrderItemsRepository orderItemsRepository;

    public List<OrderItemDTO> findOrderItemsByOrderId(Long orderId) {
        return orderItemMapper.map(orderItemsRepository.findByOrderId(orderId));
    }
}
