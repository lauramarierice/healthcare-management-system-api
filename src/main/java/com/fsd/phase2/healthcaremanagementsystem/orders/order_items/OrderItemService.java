package com.fsd.phase2.healthcaremanagementsystem.orders.order_items;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemService {

    private final OrderItemMapper orderItemMapper;

    private final OrderItemsRepository orderItemsRepository;

}
