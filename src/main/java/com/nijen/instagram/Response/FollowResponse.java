package com.nijen.instagram.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowResponse implements Serializable {
    private Boolean success;
    private String message;
    private Object data;
}
