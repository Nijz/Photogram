package com.nijen.instagram.controllers;

import com.nijen.instagram.models.User;
import com.nijen.instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getUserByJwt(
            @RequestHeader("Authorization") String jwt
    ){
        User user = userService.getUserByJwt(jwt);
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserById(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Map<String, UUID> request
            ){
        UUID userId = request.get("userId");
        User user = userService.getUserById(userId);
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
