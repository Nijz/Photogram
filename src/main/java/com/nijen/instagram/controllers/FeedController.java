package com.nijen.instagram.controllers;

import com.nijen.instagram.models.User;
import com.nijen.instagram.services.FeedService;
import com.nijen.instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getFeed(
            @RequestHeader("Authorization") String jwt
    ){
        User user = userService.getUserByJwt(jwt);
        return ResponseEntity.ok(feedService.getFeed(user));
    }
}
