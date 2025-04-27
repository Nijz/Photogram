package com.nijen.instagram.services;

import com.nijen.instagram.Response.FollowResponse;
import com.nijen.instagram.models.Follow;
import com.nijen.instagram.models.User;
import com.nijen.instagram.repository.FollowRepository;
import com.nijen.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Override
    @CachePut(value = "FOLLOW", key = "#follower.id")
    public FollowResponse followUser(User follower, User followed) {
        Follow follow = new Follow();
        follow.setFollowerId(follower);
        follow.setFollowedId(followed);
        follow.setFollowedAt(LocalDateTime.now());

        followRepository.save(follow);

        List<Follow> follows = followRepository.findByFollowedId(followed);
        FollowResponse response = new FollowResponse();
        response.setSuccess(true);
        response.setMessage("Follow added successfully");
        response.setData(follows.stream().map(Follow::getFollowerId).toList());
        return response;
    }

    @Override
    @CachePut(value = "FOLLOW", key = "#follower.id")
    public FollowResponse unfollowUser(User follower, User followed) {
        int rowsDeleted = followRepository.deleteByFollowerIdAndFollowedId(follower, followed);
        FollowResponse response = new FollowResponse();
        List<Follow> follows = followRepository.findByFollowedId(followed);
        response.setSuccess(true);
        response.setMessage("Follow removed successfully");
        response.setData(follows.stream().map(Follow::getFollowerId).toList());
        return response;
    }

    @Override
    @Cacheable(value = "FOLLOW", key = "#user.id")
    public FollowResponse getFollowers(User user) {
        FollowResponse response = new FollowResponse();
        List<Follow> follows = followRepository.findByFollowedId(user);
        response.setSuccess(true);
        response.setMessage("Followers retrieved successfully");
        response.setData(follows.stream().map(Follow::getFollowerId).toList());
        return response;
    }
}
