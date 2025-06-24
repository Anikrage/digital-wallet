package com.digitalwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digitalwallet.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("SELECT w.balance FROM Wallet w WHERE w.id = :id")
    java.math.BigDecimal findBalanceById(@Param("id") Long id);
}
