package com.fsd.phase2.healthcaremanagementsystem.orders;

import com.fsd.phase2.healthcaremanagementsystem.carts.CartDTO;
import com.fsd.phase2.healthcaremanagementsystem.carts.CartService;
import com.fsd.phase2.healthcaremanagementsystem.carts.cart_items.CartItemDTO;
import com.fsd.phase2.healthcaremanagementsystem.exceptions.EntityNotFoundException;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemEntity;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final CartService cartService;

    private final OrderItemsRepository orderItemsRepository;

    public OrderDTO getOrderStatusById(Long id) {
        return orderMapper.map(orderRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Order not found.");
                }));
    }

    public List<OrderDTO> findOrdersByUserId(Long userId) {
        return orderMapper.map(orderRepository.findByUserId(userId));
    }

    public OrderDTO placeNewOrder(Long userId) {

        CartDTO cartDTO = cartService.getCartByUserId(userId);
        List<CartItemDTO> cartItems = cartDTO.getCartItems();

        OrderEntity newOrderEntity = new OrderEntity();
        newOrderEntity.setUserId(userId);
        newOrderEntity.setCreatedDate(Date.from(Instant.now()));
        orderRepository.save(newOrderEntity);

        cartItems.stream().forEach(cartItemDTO -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setCreatedDate(Date.from(Instant.now()));
            orderItemEntity.setMedicineId(cartItemDTO.getMedicineId());
            orderItemEntity.setQuantity(cartItemDTO.getQuantity());
            orderItemEntity.setOrderId(newOrderEntity.getOrderId());

            orderItemsRepository.save(orderItemEntity);
        });

        return orderMapper.map(newOrderEntity);
    }
}
