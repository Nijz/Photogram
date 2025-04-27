package com.nijen.instagram.services;

import com.nijen.instagram.models.User;

import java.util.UUID;

public interface UserService {
    public User getUserByJwt(String jwt);
    public User getUserById(UUID userId);
}
