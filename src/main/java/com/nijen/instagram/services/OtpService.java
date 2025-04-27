package com.nijen.instagram.services;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    private final StringRedisTemplate redisTemplate;
    private final JavaMailSender mailSender;
    private static final int EXPIRATION_TIME_MINUTES = 5;

    public OtpService(StringRedisTemplate redisTemplate, JavaMailSender mailSender) {
        this.redisTemplate = redisTemplate;
        this.mailSender = mailSender;
    }

    // Generate a random 6 digit OTP and store it in Redis
    public String generateOtp(String email){
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        String key = "OTP: " + email;
        redisTemplate.opsForValue().set(key, otp, EXPIRATION_TIME_MINUTES, TimeUnit.MINUTES);
        sendOtp(email, otp);
        return otp;
    }

    private void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Instagram OTP");
        message.setText("Your OTP code is: " + otp + "\nThis OTP is valid for " + EXPIRATION_TIME_MINUTES + " minutes.");
        mailSender.send(message);
    }

    public boolean validateOtp(String email, String otp){
        String key = "OTP: " + email;
        String storedOtp = redisTemplate.opsForValue().get(key);

        if (storedOtp != null && storedOtp.equals(otp)){
            redisTemplate.delete(key);
            return true;
        }

        return false;
    }
}
