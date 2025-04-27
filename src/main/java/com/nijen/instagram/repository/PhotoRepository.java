package com.nijen.instagram.repository;

import com.nijen.instagram.models.Photo;
import com.nijen.instagram.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {

    @Query("SELECT p FROM Photo p WHERE p.userId IN :users")
    List<Photo> findAllByUserIn(@Param("users") List<User> users);

    List<Photo> findByUserId(User user);
}
