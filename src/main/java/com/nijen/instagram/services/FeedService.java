package com.nijen.instagram.services;

import com.nijen.instagram.DTO.PhotoUploadedEvent;
import com.nijen.instagram.Response.FeedResponse;
import com.nijen.instagram.Response.MainFeedResponse;
import com.nijen.instagram.models.User;

import java.util.List;

public interface FeedService {
    public MainFeedResponse getFeed(User user);
    public void handlePhotoUpload(PhotoUploadedEvent event);
    public void evictFeed(User user);
}
