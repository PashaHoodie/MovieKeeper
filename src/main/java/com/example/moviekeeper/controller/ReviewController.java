package com.example.moviekeeper.controller;

import com.example.moviekeeper.dto.Review.ReviewAllArgsDTO;
import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.Review;
import com.example.moviekeeper.entity.user.User;
import com.example.moviekeeper.service.ReviewService;
import com.example.moviekeeper.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<String> addReview(@Valid @RequestBody ReviewAllArgsDTO reviewAllArgsDTO){
        Review review = reviewService.saveReview(ConverterOfDTO.getAllArgsReviewDTO(reviewAllArgsDTO));
        if (review == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{reviewId}/{userId}")
    public ResponseEntity<String> deleteReview(@PathVariable long reviewId, @PathVariable long userId, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (reviewService.deleteReview(user, reviewId, userId)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<Review>> getReviewsOfMovie(@Valid @RequestBody Movie movie){
        List<Review> reviewList = reviewService.getAllReviewsByMovie(movie);
        if (reviewList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviewList);
    }


}
