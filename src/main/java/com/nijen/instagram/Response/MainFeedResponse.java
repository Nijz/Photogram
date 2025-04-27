package com.nijen.instagram.Response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainFeedResponse implements Serializable {
    private Boolean success;
    private String message;
    private List<FeedResponse> data;

}
