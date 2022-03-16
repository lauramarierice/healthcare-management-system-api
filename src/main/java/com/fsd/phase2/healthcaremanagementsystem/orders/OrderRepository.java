package com.fsd.phase2.healthcaremanagementsystem.orders;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {

    Optional<OrderEntity> findById(Long id);

    List<OrderEntity> findByUserId(Long userId);

    @Query(value = " select o.orderDate as orderDate," +
            "              o.orderId as orderId," +
            "              o.userId as userId," +
            "              o.orderStatus as orderStatus" +
            "   from OrderEntity o" +
            "   order by o.orderDate")
    List<OrderProjection> findAllOrders();

    @Query(value = " select o.orderDate as orderDate," +
            "              o.orderId as orderId," +
            "              o.userId as userId," +
            "              o.orderStatus as orderStatus" +
            "   from OrderEntity o" +
            "   where o.orderDate >= :date" +
            "   order by o.orderDate")
    List<OrderProjection> findByOrdersByAfterDate(@Param("date") LocalDate date);
}
