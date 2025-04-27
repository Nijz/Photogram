package com.nijen.instagram.services;

import com.nijen.instagram.DTO.PhotoUploadedEvent;
import com.nijen.instagram.Response.FeedResponse;
import com.nijen.instagram.Response.MainFeedResponse;
import com.nijen.instagram.models.Follow;
import com.nijen.instagram.models.Photo;
import com.nijen.instagram.models.User;
import com.nijen.instagram.repository.FollowRepository;
import com.nijen.instagram.repository.PhotoRepository;
import com.nijen.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FeedServiceImpl implements FeedService{

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private FollowService followService;

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void handlePhotoUpload(PhotoUploadedEvent event) {

        Optional<User> userOpt = userRepository.findById(event.getUserId());
        if (userOpt.isEmpty()){
            throw new RuntimeException("User not found");
        }

        User postingUser = userOpt.get();
        List<Follow> followers = followRepository.findByFollowedId(postingUser);
        if (followers.isEmpty()){
            throw new RuntimeException("No Following found");
        }

        FeedResponse feed = new FeedResponse();
        feed.setUsername(postingUser.getUsername());
        feed.setPhotoUrl(event.getPhotoUrl());
        feed.setCaption(event.getCaption());
        feed.setCreatedAt(event.getCreatedAt());

        followers.forEach(follow -> {
            UUID followerId = follow.getFollowerId().getId();
            Cache cache = Objects.requireNonNull(cacheManager.getCache("FEED"));
            Cache.ValueWrapper valueWrapper = cache.get(followerId);

            if (valueWrapper != null && valueWrapper.get() != null) {
                MainFeedResponse existingFeed = (MainFeedResponse) valueWrapper.get();
                List<FeedResponse> updateFeedList = new ArrayList<>();
                updateFeedList.add(feed);
                updateFeedList.addAll(existingFeed.getData());
                existingFeed.setData(updateFeedList);

                cache.put(followerId, existingFeed);
            } else {
                System.out.println("No cache found for user " + followerId + " in FEED, skipping update.");
            }
        });

    }

    @Override
    @Cacheable(value = "FEED", key = "#user.id")
    public MainFeedResponse getFeed(User user) {
        System.out.println(user.getId());
        MainFeedResponse res = new MainFeedResponse();

        List<Follow> follows = followRepository.findByFollowedId(user);

        if (follows.isEmpty()){
            res.setSuccess(false);
            res.setMessage("No Following found");
            return res;
        }

        List<User> followedUsers = follows.stream().map(Follow::getFollowerId).toList();
        List<Photo> photos = photoRepository.findAllByUserIn(followedUsers);

        if (photos.isEmpty()){
            res.setSuccess(false);
            res.setMessage("No Photos found");
            return res;
        }

        List<FeedResponse> feedItems = new java.util.ArrayList<>(photos.stream().map(photo -> {
            FeedResponse feed = new FeedResponse();
            feed.setUsername(photo.getUserId().getUsername());
            feed.setPhotoUrl(photo.getPhotoUrl());
            feed.setCaption(photo.getCaption());
            feed.setCreatedAt(photo.getCreatedAt());
            return feed;
        }).toList());

        feedItems.sort((a,b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

        res.setSuccess(true);
        res.setMessage("Feed Fetched");
        res.setData(feedItems);
        return res;
    }

    @Override
    @CacheEvict(value = "FEED", key = "#user.id")
    public void evictFeed(User user) {
        System.out.println("Evicting feed for user " + user.getId());
    }


}
