package com.nijen.instagram.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nijen.instagram.models.Photo;
import com.nijen.instagram.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PhotoService {
    public Boolean uploadPhoto(User user, MultipartFile file, String caption) throws IOException;
    public List<Photo> getPhotosByUser(User userId);
}
