package com.nijen.instagram.services;

import com.nijen.instagram.Response.FollowResponse;
import com.nijen.instagram.models.User;

import java.util.List;

public interface FollowService {
    public FollowResponse followUser(User follower, User followed);
    public FollowResponse unfollowUser(User follower, User followed);
    public FollowResponse getFollowers(User user);
}
