package com.nijen.instagram.controllers;

import com.nijen.instagram.Response.AuthResponse;
import com.nijen.instagram.models.User;
import com.nijen.instagram.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/auth/")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser (@RequestBody User user) {
        AuthResponse response = authService.registerUser(user);
        if (!response.getSuccess()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        AuthResponse response = authService.loginUser(email, password);

        if (!response.getSuccess()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/verify-otp/")
    public ResponseEntity<?> verifyOtp (@RequestBody User user, @RequestParam String otp) {
        AuthResponse response = authService.verifyOtp(user, otp);
        if (!response.getSuccess()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp (@RequestBody String email) {
        AuthResponse response = authService.resendOtp(email);
        if (!response.getSuccess()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
