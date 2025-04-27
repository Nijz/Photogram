package com.nijen.instagram.repository;

import com.nijen.instagram.models.UserFollower;
import com.nijen.instagram.models.UserFollowerKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserFollowerRepository extends CassandraRepository<UserFollower, UserFollowerKey> {
    List<UserFollower> findByKeyUserId(UUID userId);
}
