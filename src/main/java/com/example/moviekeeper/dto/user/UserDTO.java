package com.example.moviekeeper.dto.user;

import com.example.moviekeeper.dto.telephone.TelephoneDTO;
import com.example.moviekeeper.entity.user.Role;
import com.example.moviekeeper.utils.MessageManager;
import com.example.moviekeeper.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    private String name;

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.EMAIL, message = MessageManager.EMAIL_ERROR)
    private String username;

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.PASSWORD, message = MessageManager.EMAIL_ERROR)
    private String password;

    private TelephoneDTO telephone;

    private Role role;
}
