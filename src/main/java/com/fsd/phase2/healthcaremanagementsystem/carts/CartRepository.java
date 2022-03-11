package com.fsd.phase2.healthcaremanagementsystem.carts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> getByCartId(Long cartId);

    Optional<CartEntity> getByUserId(Long userId);

    void deleteByUserId(Long userId);
}
