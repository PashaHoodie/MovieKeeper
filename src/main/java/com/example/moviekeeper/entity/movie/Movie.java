package com.example.moviekeeper.entity.movie;


import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movies")
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
