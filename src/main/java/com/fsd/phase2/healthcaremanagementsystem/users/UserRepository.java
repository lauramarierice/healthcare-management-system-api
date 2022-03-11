package com.fsd.phase2.healthcaremanagementsystem.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByUserIdAndPassword(Long userId, String password);
}
