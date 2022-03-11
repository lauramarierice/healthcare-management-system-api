package com.fsd.phase2.healthcaremanagementsystem.orders;

import com.fsd.phase2.healthcaremanagementsystem.carts.CartDTO;
import com.fsd.phase2.healthcaremanagementsystem.carts.CartService;
import com.fsd.phase2.healthcaremanagementsystem.carts.cart_items.CartItemDTO;
import com.fsd.phase2.healthcaremanagementsystem.exceptions.EntityNotFoundException;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemDTO;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemEntity;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemService;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final CartService cartService;

    private final OrderItemService orderItemService;

    private final OrderItemsRepository orderItemsRepository;

    public List<OrderDTO> getOrderReports() {
        return orderMapper.mapProjectionList(orderRepository.findAllOrders());
    }

    public OrderDTO getOrderStatusByOrderId(Long orderId) {
        OrderDTO orderDTO = orderMapper.map(orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new));

        List<OrderItemDTO> orderItems = orderItemService.findOrderItemsByOrderId(orderId);
        orderDTO.setOrderItems(orderItems);

        return orderDTO;
    }

    public List<OrderDTO> findOrdersByUserId(Long userId) {
        return orderMapper.mapList(orderRepository.findByUserId(userId));
    }

    public OrderDTO placeNewOrder(Long userId) {

        CartDTO cartDTO = cartService.getCartByUserId(userId);
        List<CartItemDTO> cartItems = cartDTO.getCartItems();

        OrderEntity newOrderEntity = new OrderEntity();
        newOrderEntity.setUserId(userId);
        newOrderEntity.setOrderDate(ZonedDateTime.now());
        orderRepository.save(newOrderEntity);

        cartItems.forEach(cartItemDTO -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setMedicineId(cartItemDTO.getMedicineId());
            orderItemEntity.setQuantity(cartItemDTO.getQuantity());
            orderItemEntity.setOrderId(newOrderEntity.getOrderId());

            orderItemsRepository.save(orderItemEntity);
        });

        return orderMapper.map(newOrderEntity);
    }
}
