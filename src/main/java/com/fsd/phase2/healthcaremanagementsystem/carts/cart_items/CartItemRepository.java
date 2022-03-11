package com.fsd.phase2.healthcaremanagementsystem.carts.cart_items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByCartIdAndMedicineId(Long cartId, Long medicineId);

    @Query(value = "select ci.cartId as cartId," +
            "       ci.cartItemId as cartItemId," +
            "       ci.medicineId as medicineId," +
            "       ci.quantity as quantity," +
            "       m.medicineName as medicineName," +
            "       m.price as price" +
            " from CartItemEntity ci" +
            " inner join MedicineEntity m on m.medicineId = ci.medicineId" +
            " where ci.cartId = :cartId")
    List<CartItemProjection> findByCartId(Long cartId);

    void deleteByCartIdAndMedicineId(Long cartId, Long medicineId);
}
