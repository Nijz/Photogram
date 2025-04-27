package com.nijen.instagram.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nijen.instagram.models.User;
import com.nijen.instagram.services.PhotoService;
import com.nijen.instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("caption") String caption,
            @RequestHeader("Authorization") String jwt
    ) throws IOException {


        User user = userService.getUserByJwt(jwt);

        Boolean success = photoService.uploadPhoto(user, file, caption);
        if (!success){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Photo upload failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Photo uploaded successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPhotosByUser(
            @RequestHeader("Authorization") String jwt
    ){
        User user = userService.getUserByJwt(jwt);
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.status(HttpStatus.OK).body(photoService.getPhotosByUser(user));
    }
}
