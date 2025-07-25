package com.digitalwallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalwallet.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromWalletIdOrToWalletIdOrderByTimestampDesc(Long fromWalletId, Long toWalletId);
}
