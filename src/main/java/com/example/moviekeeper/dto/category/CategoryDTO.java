package com.example.moviekeeper.dto.category;

import com.example.moviekeeper.entity.movie.FilmStudio;
import com.example.moviekeeper.entity.movie.MoviesGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDTO {
    private MoviesGenre genre;
    private FilmStudio studio;
}
