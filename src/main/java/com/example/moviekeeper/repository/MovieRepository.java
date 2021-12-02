package com.example.moviekeeper.repository;

import com.example.moviekeeper.entity.movie.FilmStudio;
import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.MoviesGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByNameAndGenre(String name, MoviesGenre genre);

//    boolean existsByStudioAndGenre(MoviesGenre genre, FilmStudio studio);
//
//    boolean existsByGenre(MoviesGenre genre);
//
//    boolean existsByStudio(FilmStudio studio);

    Optional<Movie> findByName(String name);

    List<Movie> getAllByGenre(MoviesGenre genre);

    List<Movie> getAllByStudio(FilmStudio studio);
}
