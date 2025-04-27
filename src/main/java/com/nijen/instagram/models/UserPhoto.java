package com.nijen.instagram.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value = "user_photos", keyspace = "instagram")
public class UserPhoto {

    @PrimaryKey
    private UserPhotoKey key;

    @Column("photo_url")
    private String photoUrl;

    private String caption;
}
