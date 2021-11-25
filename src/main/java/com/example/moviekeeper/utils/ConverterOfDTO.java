package com.example.moviekeeper.utils;


import com.example.moviekeeper.dto.Review.ReviewAllArgsDTO;
import com.example.moviekeeper.dto.movie.MovieAllArgsDTO;
import com.example.moviekeeper.dto.telephone.TelephoneDTO;
import com.example.moviekeeper.dto.user.UserDTO;
import com.example.moviekeeper.entity.movie.Movie;
import com.example.moviekeeper.entity.movie.Review;
import com.example.moviekeeper.entity.user.Role;
import com.example.moviekeeper.entity.user.Telephone;
import com.example.moviekeeper.entity.user.User;

public class ConverterOfDTO {

    public static User getAllArgsUserDTO (UserDTO userDTO) {
    return User.builder()
            .name(userDTO.getName())
            .username(userDTO.getUsername())
            .password(userDTO.getPassword())
            .telephone(Telephone.builder()
                    .number(userDTO.getTelephone().getNumber())
                    .build())
            .role(Role.USER)
            .build();
    }

    public static User getUserAuthDTO (User user){
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static Telephone getTelephoneDTO (TelephoneDTO telephoneDTO){
        return Telephone.builder()
                .number(telephoneDTO.getNumber())
                .build();
    }

    public static Movie getAllArgsMovieDTO (MovieAllArgsDTO movieAllArgsDTO){
        return Movie.builder()
                .name(movieAllArgsDTO.getName())
                .genre(movieAllArgsDTO.getCategoryDTO().getGenre())
                .studio(movieAllArgsDTO.getCategoryDTO().getStudio())
                .build();
    }

    public static Review getAllArgsReviewDTO (ReviewAllArgsDTO reviewAllArgsDTO, long userId) {
        return Review.builder()
                .id(reviewAllArgsDTO.getReviewId())
                .description(reviewAllArgsDTO.getDescription())
                .user(User.builder().id(userId).build()).build();
    }

}
