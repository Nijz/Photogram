package com.nijen.instagram.services;

import com.nijen.instagram.Response.AuthResponse;
import com.nijen.instagram.models.User;
import com.nijen.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public AuthResponse registerUser(User user) {

        AuthResponse res = new AuthResponse();

        User u = userRepository.findByEmail(user.getEmail());

        if (u != null){
            res.setSuccess(false);
            res.setError(new Error("User already exists with email"));
            res.setMessage("User already exists with email: " + user.getEmail());
            return res;
        }

        String otp = otpService.generateOtp(user.getEmail());
        res.setSuccess(true);
        res.setMessage("OTP sent to your email. Please verify to complete registration.");
        return res;

    }

    @Override
    public AuthResponse loginUser(String email, String password) {
        AuthResponse res = new AuthResponse();
        User user = userRepository.findByEmail(email);

        if (user == null) {
            res.setSuccess(false);
            res.setMessage("User not found");
            res.setError(new Error("User not found"));
            return res;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            res.setSuccess(false);
            res.setMessage("Invalid password");
            res.setError(new Error("Invalid password"));
            return res;
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(user.getEmail());

        if (userDetails == null) {
            res.setSuccess(false);
            res.setMessage("User not found");
            res.setError(new Error("User not found"));
            return res;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, null);

        if (authentication.isAuthenticated()){
            String token = jwtService.generateToken(user.getEmail(), authentication);
            res.setSuccess(true);
            res.setMessage("User logged in successfully ❤️");
            res.setData(token);
            return res;
        }

        res.setSuccess(false);
        res.setMessage("Invalid credentials");
        res.setError(new Error("Invalid credentials"));
        return res;

    }

    @Override
    public AuthResponse verifyOtp(User user, String otp) {

        AuthResponse res = new AuthResponse();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (!otpService.validateOtp(user.getEmail(), otp)) {
            res.setSuccess(false);
            res.setMessage("Invalid OTP");
            res.setError(new Error("Invalid OTP"));
            return res;
        }

        userRepository.save(user);
        res.setSuccess(true);
        res.setMessage("OTP verified successfully");
        res.setData(user);
        return res;
    }

    @Override
    public AuthResponse resendOtp(String email) {
        AuthResponse res = new AuthResponse();
        String otp = otpService.generateOtp(email);
        res.setSuccess(true);
        res.setMessage("OTP sent to " + email);
        return res;
    }
}
