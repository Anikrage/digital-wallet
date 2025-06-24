package com.digitalwallet.service;

import org.springframework.stereotype.Service;

import com.digitalwallet.model.User;
import com.digitalwallet.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(String name, String email) {
        if (userRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepo.save(user);
    }
}
