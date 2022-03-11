package com.fsd.phase2.healthcaremanagementsystem.orders;

import com.fsd.phase2.healthcaremanagementsystem.orders.order_items.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class OrderDTO {

    private Long orderId;

    private ZonedDateTime orderDate;

    private Long userId;

    private String orderStatus;

    private List<OrderItemDTO> orderItems;
}
