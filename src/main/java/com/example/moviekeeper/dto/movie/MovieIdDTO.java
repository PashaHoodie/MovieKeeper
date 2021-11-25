package com.example.moviekeeper.dto.movie;


import com.example.moviekeeper.utils.MessageManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieIdDTO {
    private long movieId;

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    private String name;
}
