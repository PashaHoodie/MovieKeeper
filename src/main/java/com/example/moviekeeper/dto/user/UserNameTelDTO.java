package com.example.moviekeeper.dto.user;

import com.example.moviekeeper.dto.telephone.TelephoneDTO;
import com.example.moviekeeper.utils.MessageManager;
import com.example.moviekeeper.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserNameTelDTO {

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.EMAIL, message = MessageManager.EMAIL_ERROR)
    private String name;

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Size(min = 7, max = 12, message = MessageManager.PHONE_NUMBER_ERROR)
    private String number;

    private TelephoneDTO telephone;
}
