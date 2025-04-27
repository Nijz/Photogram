package com.nijen.instagram.services;

import com.nijen.instagram.Response.AuthResponse;
import com.nijen.instagram.models.User;

public interface AuthService {
    public AuthResponse registerUser(User user);
    public AuthResponse loginUser(String email, String password);
    public AuthResponse verifyOtp(User user, String otp);
    public AuthResponse resendOtp(String email);
}
