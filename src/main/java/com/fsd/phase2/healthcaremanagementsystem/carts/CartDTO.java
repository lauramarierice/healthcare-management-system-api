package com.fsd.phase2.healthcaremanagementsystem.carts;

import com.fsd.phase2.healthcaremanagementsystem.carts.cart_items.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CartDTO {

    private Long cartId;

    private List<CartItemDTO> cartItems;

    private Double cartCost;
}
