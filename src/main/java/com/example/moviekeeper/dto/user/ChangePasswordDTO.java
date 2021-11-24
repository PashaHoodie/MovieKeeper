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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordDTO {

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.PASSWORD, message = MessageManager.PASSWORD_ERROR)
    private String oldPassword;

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.PASSWORD, message = MessageManager.PASSWORD_ERROR)
    private String newPassword;
}
