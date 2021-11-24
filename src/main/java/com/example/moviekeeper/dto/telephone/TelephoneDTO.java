package com.example.moviekeeper.dto.telephone;

import com.example.moviekeeper.utils.MessageManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelephoneDTO {

    private long id;

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    @Size(min = 7, max = 12, message = MessageManager.PHONE_NUMBER_ERROR)
    private String number;
}
