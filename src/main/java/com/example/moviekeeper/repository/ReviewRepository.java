package com.example.moviekeeper.repository;

import com.example.moviekeeper.entity.movie.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "FROM Review WHERE user.id =:userId")
    boolean existsByUserId(long userId);

    Optional<Review> findByIdAndDescription(long id, String description);


    @Query(value = "FROM Review WHERE movie.id =:movieId")
    List<Review> getAllByMovieId(long movieId);

}
