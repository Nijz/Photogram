package com.nijen.instagram.controllers;

import com.nijen.instagram.Response.FollowResponse;
import com.nijen.instagram.models.User;
import com.nijen.instagram.services.FollowService;
import com.nijen.instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @PostMapping("/")
    public ResponseEntity<?> followUser(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Map<String, UUID> request
    ){

        UUID userId = request.get("userId");
        User followUser = userService.getUserById(userId);

        if (followUser == null){
            return ResponseEntity.status(400).body("User not found");
        }

        User user = userService.getUserByJwt(jwt);
        FollowResponse response = followService.followUser(followUser, user);

        if (!response.getSuccess()){
            return ResponseEntity.status(400).body(response);
        }

        return ResponseEntity.status(200).body("Followed successfully");
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<?> unfollowUser(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Map<String, UUID> request
    ){
        UUID userId = request.get("userId");
        User unfollowUser = userService.getUserById(userId);

        if (unfollowUser == null){
            return ResponseEntity.status(400).body("User not found");
        }

        User user = userService.getUserByJwt(jwt);
        FollowResponse response = followService.unfollowUser(unfollowUser, user);

        if (!response.getSuccess()){
            return ResponseEntity.status(400).body("Unfollow failed");
        }

        return ResponseEntity.status(200).body("Unfollowed successfully");
    }

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(
            @RequestHeader("Authorization") String jwt
    ){
        User user = userService.getUserByJwt(jwt);
        if (user == null){
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.status(200).body(followService.getFollowers(user));
    }
}
