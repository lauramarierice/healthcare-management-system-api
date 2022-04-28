package com.fsd.phase2.healthcaremanagementsystem.carts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> getByCartId(Long cartId);

    Optional<CartEntity> getByUserId(Long userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteByUserId(Long userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteByCartId(Long cartId);
}
