package com.digitalwallet.controller;

import com.digitalwallet.model.User;
import com.digitalwallet.repository.UserRepository;
import com.digitalwallet.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Test
    void fullWalletFlow() throws Exception {
        // Create user
        String userJson = "{\"name\":\"Test User\",\"email\":\"testuser@example.com\"}";
        String userResponse = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        // Extract user ID (simple way, for demonstration)
        Long userId = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals("testuser@example.com"))
                .findFirst().get().getId();

        // Create wallet
        mockMvc.perform(post("/api/wallets/create")
                .param("userId", userId.toString())
                .param("initialBalance", "150.00"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.balance").value(150.00));

        // Add money
        Long walletId = walletRepository.findAll().stream()
                .filter(w -> w.getUserId().equals(userId))
                .findFirst().get().getId();

        mockMvc.perform(post("/api/wallets/" + walletId + "/add")
                .param("amount", "50.00"))
            .andExpect(status().isOk());

        // Check balance
        mockMvc.perform(get("/api/wallets/" + walletId + "/balance"))
            .andExpect(status().isOk())
            .andExpect(content().string("200.00"));
    }
}
