// src/main/java/com/digitalwallet/service/WalletService.java
package com.digitalwallet.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalwallet.model.Transaction;
import com.digitalwallet.model.Wallet;
import com.digitalwallet.repository.TransactionRepository;
import com.digitalwallet.repository.WalletRepository;

@Service
public class WalletService {
    private final WalletRepository walletRepo;
    private final TransactionRepository txRepo;

    public WalletService(WalletRepository walletRepo, TransactionRepository txRepo) {
        this.walletRepo = walletRepo;
        this.txRepo = txRepo;
    }

    @Transactional
    public void addMoney(Long walletId, BigDecimal amount) {
        Wallet wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepo.save(wallet);

        Transaction tx = new Transaction();
        tx.setFromWalletId(null);
        tx.setToWalletId(walletId);
        tx.setAmount(amount);
        tx.setTimestamp(LocalDateTime.now());
        tx.setType("CREDIT");
        txRepo.save(tx);
    }

    @Transactional
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        if (fromWalletId.equals(toWalletId)) throw new IllegalArgumentException("Cannot transfer to self");
        Wallet fromWallet = walletRepo.findById(fromWalletId)
                .orElseThrow(() -> new IllegalArgumentException("Sender wallet not found"));
        Wallet toWallet = walletRepo.findById(toWalletId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver wallet not found"));

        if (fromWallet.getBalance().compareTo(amount) < 0) throw new IllegalArgumentException("Insufficient funds");

        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        toWallet.setBalance(toWallet.getBalance().add(amount));
        walletRepo.save(fromWallet);
        walletRepo.save(toWallet);

        Transaction debit = new Transaction();
        debit.setFromWalletId(fromWalletId);
        debit.setToWalletId(toWalletId);
        debit.setAmount(amount);
        debit.setTimestamp(LocalDateTime.now());
        debit.setType("DEBIT");
        txRepo.save(debit);

        Transaction credit = new Transaction();
        credit.setFromWalletId(fromWalletId);
        credit.setToWalletId(toWalletId);
        credit.setAmount(amount);
        credit.setTimestamp(LocalDateTime.now());
        credit.setType("CREDIT");
        txRepo.save(credit);
    }

    public BigDecimal getBalance(Long walletId) {
        BigDecimal balance = walletRepo.findBalanceById(walletId);
        if (balance == null) throw new IllegalArgumentException("Wallet not found");
        return balance;
    }

    public List<Transaction> getTransactions(Long walletId) {
        return txRepo.findByFromWalletIdOrToWalletIdOrderByTimestampDesc(walletId, walletId);
    }
    public Wallet createWallet(Long userId, BigDecimal initialBalance) {
    Wallet wallet = new Wallet();
    wallet.setUserId(userId);
    wallet.setBalance(initialBalance != null ? initialBalance : BigDecimal.ZERO);
    wallet.setVersion(0);
    return walletRepo.save(wallet);
}

}
