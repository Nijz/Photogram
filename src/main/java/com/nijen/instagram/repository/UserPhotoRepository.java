package com.nijen.instagram.repository;

import com.nijen.instagram.models.UserPhoto;
import com.nijen.instagram.models.UserPhotoKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserPhotoRepository extends CassandraRepository<UserPhoto, UserPhotoKey> {
    List<UserPhoto> findByKeyUserId(UUID userId);
}
