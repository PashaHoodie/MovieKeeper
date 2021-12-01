package com.example.moviekeeper.aspect;

import com.example.moviekeeper.dto.Review.ReviewAllArgsDTO;
import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.Review;
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
public class ReviewLogAspect {

    private final Logger logger = LoggerFactory.getLogger(MovieLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.moviekeeper.controller.ReviewController.addReview(..)) && args(reviewAllArgsDTO, ..))")
    public void saveReview(ReviewAllArgsDTO reviewAllArgsDTO){}

    @AfterReturning(value = "saveReview(reviewAllArgsDTO)", argNames = "reviewAllArgsDTO, returningValue", returning = "returningValue")
    public void logOfSaveReview(ReviewAllArgsDTO reviewAllArgsDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.CREATED)){
            logger.info("New comment ' {} ' added.", reviewAllArgsDTO.getDescription());
        }else{
            logger.info("Comment ' {} ' added unsuccessfully", reviewAllArgsDTO.getDescription());
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.ReviewController.deleteReview(..)) && args(id, ..))")
    public void deleteReview(long id){}

    @AfterReturning(value = "deleteReview(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfDeleteReview(long id, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Review with id = {} successfully deleted.", id);
        }else{
            logger.info("Review with id = {} deletion failed!", id);
        }
    }

    @Pointcut("execution(public * com.example.moviekeeper.controller.ReviewController.getReviewsOfMovie(..)) && args(movie, ..))")
    public void getReviewsOfMovie(Movie movie){}

    @AfterReturning(value = "getReviewsOfMovie(movie)", argNames = "movie, returningValue", returning = "returningValue")
    public void logOfGetReviewsOfMovie(Movie movie, ResponseEntity<List<Review>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Reviews of {} successfully found.", movie.getName());
        }else{
            logger.info("Reviews of = {} not found!", movie.getName());
        }
    }
}
