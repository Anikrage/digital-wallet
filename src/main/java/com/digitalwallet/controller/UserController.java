package com.digitalwallet.controller;

import com.digitalwallet.model.User;
import com.digitalwallet.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    public static class CreateUserRequest {
        @NotBlank public String name;
        @NotBlank @Email public String email;
        // getters/setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest req) {
        User user = userService.createUser(req.getName(), req.getEmail());
        return ResponseEntity.ok(user);
    }
}
