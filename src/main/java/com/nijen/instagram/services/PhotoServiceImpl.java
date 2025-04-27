package com.nijen.instagram.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nijen.instagram.DTO.PhotoUploadedEvent;
import com.nijen.instagram.models.*;
import com.nijen.instagram.repository.FollowRepository;
import com.nijen.instagram.repository.PhotoRepository;
import com.nijen.instagram.repository.UserPhotoRepository;
import com.nijen.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private FeedService feedService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private MinioService minioService;

    @Autowired
    private UserPhotoRepository userPhotoRepository;

    @Override
    public Boolean uploadPhoto(User user, MultipartFile file, String caption) throws IOException {

        Photo photo = new Photo();
        PhotoUploadedEvent event = new PhotoUploadedEvent();

        if (user == null || file == null){
            return false;
        }

        String url = minioService.uploadFile(file);
//        UUID photoId = UUID.randomUUID();
//        UserPhoto userPhoto = UserPhoto.builder()
//                .key(UserPhotoKey.builder()
//                        .userId(user.getId())
//                        .photoId(photoId)
//                        .createdAt(Instant.now())
//                        .build())
//                .photoUrl(url)
//                .caption(caption)
//                .build();
//
//        userPhotoRepository.save(userPhoto);

        photo.setUserId(user);
        photo.setPhotoUrl(url);
        photo.setCaption(caption);
        photo.setCreatedAt(LocalDateTime.now());
        photoRepository.save(photo);

        List<Follow> followers = followRepository.findByFollowedId(user);


        event.setPhotoId(photo.getId());
        event.setUserId(user.getId());
        event.setPhotoUrl(url);
        event.setCaption(caption);
        event.setCreatedAt(LocalDateTime.now());
        kafkaProducerService.sendNewPostEvent(event);

        return true;
    }

    @Override
    public List<Photo> getPhotosByUser(User userId) {

        List<UserPhoto> userPhotos = userPhotoRepository.findByKeyUserId(userId.getId());

        return userPhotos.stream().map(userPhoto -> {
            Photo photo = new Photo();
            photo.setUserId(userId);
            photo.setPhotoUrl(userPhoto.getPhotoUrl());
            photo.setCaption(userPhoto.getCaption());
            return photo;
        }).toList();
    }
}
