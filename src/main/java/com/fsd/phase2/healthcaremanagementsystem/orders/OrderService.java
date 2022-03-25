package com.fsd.phase2.healthcaremanagementsystem.orders;

import com.fsd.phase2.healthcaremanagementsystem.carts.CartDTO;
import com.fsd.phase2.healthcaremanagementsystem.carts.CartService;
import com.fsd.phase2.healthcaremanagementsystem.carts.cart_items.CartItemDTO;
import com.fsd.phase2.healthcaremanagementsystem.commons.FilterByTypeEnum;
import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.BadRequestException;
import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.InsufficientFundsException;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemDTO;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemEntity;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemService;
import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemsRepository;
import com.fsd.phase2.healthcaremanagementsystem.users.accounts.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final CartService cartService;

    private final OrderItemService orderItemService;

    private final OrderItemsRepository orderItemsRepository;

    private final UserAccountService userAccountService;

    public List<OrderDTO> getOrderReports() {
        return orderMapper.mapProjectionList(orderRepository.findAllOrders());
    }

    public List<OrderDTO> getOrderReportsAndFilterByDate(String filterByType, Integer value) {

        List<OrderDTO> orders;
        LocalDate filterDate;

        if (FilterByTypeEnum.DAYS.getFilterByType().equals(filterByType)) {
            filterDate = LocalDate.now().minusDays(value);
        } else if (FilterByTypeEnum.WEEKS.getFilterByType().equals(filterByType)) {
            filterDate = LocalDate.now().minusWeeks(value);
        } else if (FilterByTypeEnum.YEARS.getFilterByType().equals(filterByType)) {
            filterDate = LocalDate.now().minusYears(value);
        } else if (FilterByTypeEnum.MONTHS.getFilterByType().equals(filterByType)) {
            filterDate = LocalDate.now().minusMonths(value);
        } else {
            throw new BadRequestException("Invalid filter date");
        }

        orders = orderMapper.mapProjectionList(orderRepository.findByOrdersByAfterDate(filterDate));

        orders.forEach(orderDTO -> {
            List<OrderItemDTO> orderItems = orderItemService.findOrderItemsByOrderId(orderDTO.getOrderId());
            orderDTO.setOrderItems(orderItems);
        });

        return orders;
    }

    public OrderDTO getOrderStatusByOrderId(Long orderId) {
        OrderDTO orderDTO = orderMapper.map(orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order details not found")));

        List<OrderItemDTO> orderItems = orderItemService.findOrderItemsByOrderId(orderId);
        orderDTO.setOrderItems(orderItems);

        return orderDTO;
    }

    public List<OrderDTO> findOrdersByUserId(Long userId) {
        return orderMapper.mapList(orderRepository.findByUserId(userId));
    }

    public OrderDTO placeNewOrder(Long userId, Long accountId) {

        CartDTO cartDTO = cartService.getCartByUserId(userId);
        List<CartItemDTO> cartItems = cartDTO.getCartItems();

        //check balance
        Double userBalance = userAccountService.getUserBalanceByUserId(userId, accountId);

        if (userBalance < cartDTO.getCartCost()) {
            throw new InsufficientFundsException("Not enough funds to place order!");
        }

        userAccountService.updateUserBalance(userId, accountId, cartDTO.getCartCost());

        OrderEntity newOrderEntity = new OrderEntity();
        newOrderEntity.setUserId(userId);
        newOrderEntity.setOrderDate(LocalDate.now());
        orderRepository.save(newOrderEntity);

        cartItems.forEach(cartItemDTO -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setMedicineId(cartItemDTO.getMedicineId());
            orderItemEntity.setQuantity(cartItemDTO.getQuantity());
            orderItemEntity.setOrderId(newOrderEntity.getOrderId());

            orderItemsRepository.save(orderItemEntity);
        });

        OrderDTO newlyPlaceOrderDetails = orderMapper.map(orderRepository.findById(newOrderEntity.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Error in creating the order, please resubmit your order")));

        List<OrderItemDTO> orderItems = orderItemService.findOrderItemsByOrderId(newOrderEntity.getOrderId());
        newlyPlaceOrderDetails.setOrderItems(orderItems);

        return newlyPlaceOrderDetails;
    }
}
