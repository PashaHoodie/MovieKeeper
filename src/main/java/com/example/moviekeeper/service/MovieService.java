package com.example.moviekeeper.service;

import com.example.moviekeeper.entity.movie.FilmStudio;
import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.MoviesGenre;
import com.example.moviekeeper.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public boolean saveMovie(Movie movie) {
        if (movieRepository.existsByNameAndGenre(movie.getName(), movie.getGenre())) {
            return false;
        }
        movieRepository.save(movie);
        return true;
    }

    public boolean deleteMovie(long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.getAllByGenre(MoviesGenre.valueOf(genre.toUpperCase(Locale.ENGLISH)));
    }

    public List<Movie> getMoviesByStudio(String studio) {
        return movieRepository.getAllByStudio(FilmStudio.valueOf(studio.toUpperCase(Locale.ENGLISH)));
    }
}


