package com.fsd.phase2.healthcaremanagementsystem.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUserIdAndPassword(Long userId, String password);

    Optional<UserEntity> findByUserNameAndPassword(String userName, String password);
}
