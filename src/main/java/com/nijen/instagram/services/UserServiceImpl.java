package com.nijen.instagram.services;

import com.nijen.instagram.models.User;
import com.nijen.instagram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public User getUserByJwt(String jwt) {
        String email = jwtService.extractUsername(jwt);
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(UUID userId) throws UsernameNotFoundException {
        Optional<User> user = Optional.of(userRepository.findById(userId).orElseThrow());
        return user.get();
    }
}
