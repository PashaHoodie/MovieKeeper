package com.example.moviekeeper.dto.movie;


import com.example.moviekeeper.utils.MessageManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieIdNameCategoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    private String name;

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    private String category;
}
