package com.example.moviekeeper.entity.movie;


import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private FilmStudio studio;

    @Enumerated(EnumType.STRING)
    private MoviesGenre genre;

}
