package com.fsd.phase2.healthcaremanagementsystem.users.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {

    Optional<UserAccountEntity> findByUserIdAndAccountNumber(Long userId, Long accountNumber);

    List<UserAccountEntity> findByUserId(Long userId);
}
