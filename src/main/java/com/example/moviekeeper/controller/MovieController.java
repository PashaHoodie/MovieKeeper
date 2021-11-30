package com.example.moviekeeper.controller;

import com.example.moviekeeper.dto.category.CategoryDTO;
import com.example.moviekeeper.dto.movie.MovieAllArgsDTO;
import com.example.moviekeeper.entity.movie.FilmStudio;
import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.MoviesGenre;
import com.example.moviekeeper.service.MovieService;
import com.example.moviekeeper.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@Valid @RequestBody MovieAllArgsDTO movieAllArgsDTO){
        Movie movie = ConverterOfDTO.getAllArgsMovieDTO(movieAllArgsDTO);
        if (movieService.saveMovie(movie)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getByGenre/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre){
        List<Movie> movieList = movieService.getMoviesByGenre(genre);
        if (movieList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieList);
    }

    @GetMapping("/getStudio/{studio}")
    public ResponseEntity<List<Movie>> getMoviesByStudio(@PathVariable String studio){
        List<Movie> movieList = movieService.getMoviesByStudio(studio);
        if (movieList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieList);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Movie> getMovieById( @PathVariable long id){
        Optional<Movie> movieById = movieService.getMovieById(id);
        return movieById.map(movie -> ResponseEntity.status(HttpStatus.OK).body(movie)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable long id){
        if (movieService.deleteMovie(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
