package com.example.moviekeeper.dto.user;


import com.example.moviekeeper.utils.MessageManager;
import com.example.moviekeeper.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNameDTO {

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.EMAIL, message = MessageManager.EMAIL_ERROR)
    private String username;
}
