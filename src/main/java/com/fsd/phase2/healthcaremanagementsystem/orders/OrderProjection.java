package com.fsd.phase2.healthcaremanagementsystem.orders;

import java.time.LocalDate;

public interface OrderProjection {

    Long getOrderId();

    String getOrderStatus();

    Long getUserId();

    LocalDate getOrderDate();
}
