package com.nijen.instagram.controllers;

import com.nijen.instagram.services.RateLimitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SomeController {

    @Autowired
    private RateLimitService rateLimitService;

    @GetMapping("/some-endpoint")
    public String someEndPoint(HttpServletRequest request, @RequestHeader("Authorization") String jwt) {
        if (!rateLimitService.isAllowed(request)) {
            return "Too many requests. Please try again later.";
        }

        // Proceed with regular endpoint logic here
        return "Request successful!";
    }
}
