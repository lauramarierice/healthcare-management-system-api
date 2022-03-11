package com.fsd.phase2.healthcaremanagementsystem.orders;

import java.time.ZonedDateTime;

public interface OrderProjection {

    Long getOrderId();

    String getOrderStatus();

    Long getUserId();

    ZonedDateTime getOrderDate();
}
