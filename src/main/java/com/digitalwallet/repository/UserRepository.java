package com.digitalwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalwallet.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
