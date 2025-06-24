// src/main/java/com/digitalwallet/controller/WalletController.java
package com.digitalwallet.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalwallet.model.Transaction;
import com.digitalwallet.service.WalletService;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/{walletId}/add")
    public ResponseEntity<String> addMoney(@PathVariable Long walletId, @RequestParam BigDecimal amount) {
        walletService.addMoney(walletId, amount);
        return ResponseEntity.ok("Money added");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam Long fromWalletId,
            @RequestParam Long toWalletId,
            @RequestParam BigDecimal amount) {
        walletService.transfer(fromWalletId, toWalletId, amount);
        return ResponseEntity.ok("Transfer successful");
    }

    @GetMapping("/{walletId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long walletId) {
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }

    @GetMapping("/{walletId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long walletId) {
        return ResponseEntity.ok(walletService.getTransactions(walletId));
    }
    @PostMapping("/create")
    public ResponseEntity<com.digitalwallet.model.Wallet> createWallet(
            @RequestParam Long userId,
            @RequestParam(required = false) BigDecimal initialBalance) {
        return ResponseEntity.ok(walletService.createWallet(userId, initialBalance));
    }

}
