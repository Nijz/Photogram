package com.nijen.instagram.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private Boolean success;
    private Error error;
    private String message;
    private Object data;
}
