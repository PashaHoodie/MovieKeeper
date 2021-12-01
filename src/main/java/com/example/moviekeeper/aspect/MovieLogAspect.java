package com.example.moviekeeper.aspect;

import com.example.moviekeeper.dto.movie.MovieAllArgsDTO;
import com.example.moviekeeper.entity.movie.Movie;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class MovieLogAspect {

    private final Logger logger = LoggerFactory.getLogger(MovieLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.moviekeeper.controller.MovieController.addMovie(..)) && args(movieAllArgsDTO, ..)")
    public void addMovie(MovieAllArgsDTO movieAllArgsDTO){}

    @AfterReturning(value = "addMovie(movieAllArgsDTO)", argNames = "movieAllArgsDTO, returningValue", returning = "returningValue")
    public void logOfAddMovie(MovieAllArgsDTO movieAllArgsDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.CREATED)){
            logger.info("New movie - {} added.", movieAllArgsDTO.getName());
        }else{
            logger.info("Unfortunately,Movie - {} not added", movieAllArgsDTO.getName());
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.MovieController.getMovieById(..)) && args(id, ..))")
    public void getMovieById(long id){}

    @AfterReturning(value = "getMovieById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetMovieById(long id, ResponseEntity<Movie> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("This is the movie with id - {}", id);
        }else{
            logger.info("Movie with id - {} not found", id);
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.MovieController.getMoviesByGenre(..)) && args(genre, ..))")
    public void getMoviesByGenre(String genre){}

    @AfterReturning(value = "getMoviesByGenre(genre)", argNames = "genre, returningValue", returning = "returningValue")
    public void logOfGetMoviesByGenre(String genre, ResponseEntity<List<Movie>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("These are movies of genre {} ", genre);
        }else{
            logger.info("There are no movies with {} genre", genre);
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.MovieController.getMoviesByStudio(..)) && args(studio, ..))")
    public void getMoviesByStudio(String studio){}

    @AfterReturning(value = "getMoviesByStudio(studio)", argNames = "studio, returningValue", returning = "returningValue")
    public void logOfGetMoviesByStudio(String studio, ResponseEntity<List<Movie>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("These are movies of {} studio ", studio);
        }else{
            logger.info("There are no movies of {} studio", studio);
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.MovieController.deleteMovieById(..)) && args(id, ..))")
    public void deleteMovieById(long id){}

    @AfterReturning(value = "deleteMovieById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfDeleteMovieById(long id, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Movie with id = {} successfully deleted", id);
        }else{
            logger.info("Movie with id = {} deletion failed!", id);
        }
    }




}
