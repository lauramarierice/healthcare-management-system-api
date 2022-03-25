package com.fsd.phase2.healthcaremanagementsystem.orders;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/reports/orders")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<OrderDTO> getOrderReports() {
        return orderService.getOrderReports();
    }

    @GetMapping(value = "/reports/orders", params = {"filterby", "value"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<OrderDTO> getOrderReportsAndFilterByDate(@RequestParam("filterby") String filterBy, @RequestParam("value") Integer value) {
        return orderService.getOrderReportsAndFilterByDate(filterBy, value);
    }

    @GetMapping(value = "/orders/{id}/statuses")
    public OrderDTO getOrderStatusByOrderId(@PathVariable("id") Long id) {
        return orderService.getOrderStatusByOrderId(id);
    }

    @GetMapping(value = "/users/{id}/orders")
    public List<OrderDTO> findOrdersByUserId(@PathVariable("id") Long userId) {
        return orderService.findOrdersByUserId(userId);
    }

    @PostMapping(value = "/users/{id}/orders", params = "accountid")
    public OrderDTO placeNewOrder(@PathVariable("id") Long userId, @RequestParam("accountid") Long accountId) {
        return orderService.placeNewOrder(userId, accountId);
    }

}
