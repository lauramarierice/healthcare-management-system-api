package com.fsd.phase2.healthcaremanagementsystem.carts.cart_items;

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
public class CartItemDTO {

    private Long cartItemId;

    private Long cartId;

    private Long medicineId;

    private String medicineName;

    private Double price;

    private Integer quantity;
}
