package com.example.moviekeeper.repository;

import com.example.moviekeeper.entity.movie.FilmStudio;
import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.MoviesGenre;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieRepositoryTest {

    private final MovieRepository movieRepository;

    private final static List<Movie> movies = new ArrayList<>();

    public MovieRepositoryTest(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @BeforeAll
    void initMovies(){
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
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movies.add(movie1);
        movies.add(movie2);
    }

    @Test
    void getMoviesByGenre() {
        assertEquals(movies, movieRepository.getAllByGenre(MoviesGenre.ACTION));
    }

    @Test
    void getMoviesByStudio() {
        assertEquals(movies, movieRepository.getAllByStudio(FilmStudio.COLUMBIA_PICTURES));
    }

    @Test
    void findByName() {
        assertEquals(movies.get(0), movieRepository.findByName(movies.get(0).getName()).get());
    }
}
