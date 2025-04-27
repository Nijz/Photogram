package com.nijen.instagram.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponse {
    private String username;
    private String photoUrl;
    private String caption;
    private LocalDateTime createdAt;
}
