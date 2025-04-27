package com.nijen.instagram.DTO;

import com.nijen.instagram.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoInputDTO {
    private User userId;
    private File file;
    private String caption;
}
