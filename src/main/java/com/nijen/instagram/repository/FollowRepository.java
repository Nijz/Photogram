package com.nijen.instagram.repository;

import com.nijen.instagram.models.Follow;
import com.nijen.instagram.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {

    List<Follow> findByFollowedId(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM Follow f WHERE f.followerId = :follower AND f.followedId = :followed")
    int deleteByFollowerIdAndFollowedId(@Param("follower") User follower, @Param("followed") User followed);

    @Query("SELECT u FROM Follow f JOIN f.followerId u WHERE f.followedId = :userId")
    List<User> findFollowersByUserId(UUID userId);

}
