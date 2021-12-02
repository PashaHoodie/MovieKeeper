package com.example.moviekeeper.service;

import com.example.moviekeeper.entity.movie.FilmStudio;
import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.MoviesGenre;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {

    private final List<Movie> movies = new ArrayList<>();

    private final MovieService movieService;

    public MovieServiceTest(MovieService movieService) {
        this.movieService = movieService;
    }

    @BeforeAll
    void initMovies() {
        Movie movie1 = Movie.builder()
                .id(1)
                .name("Avengers")
                .genre(MoviesGenre.ACTION)
                .studio(FilmStudio.MARVEL_STUDIOS)
                .build();

        Movie movie2 = Movie.builder()
                .id(2)
                .name("Bad_Boys")
                .name("Avengers")
                .genre(MoviesGenre.COMEDY)
                .studio(FilmStudio.COLUMBIA_PICTURES)
                .build();
        movies.add(movie1);
        movies.add(movie2);
        movieService.saveMovie(movie1);
        movieService.saveMovie(movie2);
    }

    @Test
    void addMovie() {
        assertEquals(movies.size(), movieService.getAll().size());
    }

    @Test
    void deleteMovieById() {
        movieService.deleteMovie(movies.get(0).getId());
        assertEquals(1, movieService.getAll().size());
    }

    @Test
    void getById() {
        assertEquals(movies.get(0), movieService.getMovieById(movies.get(0).getId()).get());
    }

    @Test
    void getMovieByName() {
        assertEquals(movies.get(0), movieService.getMovieByName(movies.get(0).getName()).get());
    }

    @Test
    void getMoviesByGenre() {
        List<Movie> moviesByGenre = movieService.getMoviesByGenre(movies.get(0).getGenre().name());
        assertEquals(2, moviesByGenre.size());
    }

    void getMoviesByStudio() {
        List<Movie> moviesByStudio = movieService.getMoviesByStudio(movies.get(0).getStudio().name());
        assertEquals(2, moviesByStudio.size());
    }
}
