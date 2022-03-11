package com.fsd.phase2.healthcaremanagementsystem.orders;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/orders/{id}/statuses")
    public OrderDTO getOrderStatusByOrderId(@PathVariable("id") Long id) {
        return orderService.getOrderStatusById(id);
    }

    @GetMapping(value = "/users/{id}/orders")
    public List<OrderDTO> findOrdersByUserId(@PathVariable("id") Long userId) {
        return orderService.findOrdersByUserId(userId);
    }

    @PostMapping(value = "/users/{id}/orders")
    public OrderDTO placeNewOrder(@PathVariable("id") Long patientId) {
        return orderService.placeNewOrder(patientId);
    }

}