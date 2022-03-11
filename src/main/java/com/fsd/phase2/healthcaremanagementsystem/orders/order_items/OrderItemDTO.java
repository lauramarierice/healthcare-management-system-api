package com.fsd.phase2.healthcaremanagementsystem.orders.order_items;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class OrderItemDTO {

    private Long id;

    private Integer quantity;

    private Long medicineId;

    private Long orderId;
}
