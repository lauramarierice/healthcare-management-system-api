package com.fsd.phase2.healthcaremanagementsystem.users.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {

    Optional<UserAccountEntity> findByUserIdAndAccountNumber(Long userId, Long accountNumber);

}
