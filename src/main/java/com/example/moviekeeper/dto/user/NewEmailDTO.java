package com.example.moviekeeper.dto.user;

import com.example.moviekeeper.utils.MessageManager;
import com.example.moviekeeper.utils.Patterns;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NewEmailDTO {

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.EMAIL, message = MessageManager.EMAIL_ERROR)
    private String newEmail;
}
