package com.example.moviekeeper.dto.movie;


import com.example.moviekeeper.dto.category.CategoryDTO;
import com.example.moviekeeper.utils.MessageManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieAllArgsDTO {

    @NotBlank(message = MessageManager.NOT_BLANK_ERROR)
    private String name;

    private CategoryDTO categoryDTO;
}
