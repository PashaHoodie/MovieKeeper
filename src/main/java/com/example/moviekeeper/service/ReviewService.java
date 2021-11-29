package com.example.moviekeeper.service;

import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.Review;
import com.example.moviekeeper.entity.user.User;
import com.example.moviekeeper.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review saveReview(Review review){
        return reviewRepository.save(review);
    }

    public boolean deleteReview(User user, long revId, long userId){
        if(reviewRepository.existsById(revId) && user.getId() == userId){
            reviewRepository.deleteById(revId);
            return true;
        }
        return false;
    }

    public List<Review> getAllReviewsByMovie(Movie movie){
        return reviewRepository.getAllByMovieId(movie.getId());
    }
}
