package com.example.blockbusterapiv3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@Entity(name = "movies") // JPA annotation to specify that this class is an entity and maps to the "movies" table in the database
public class Movie{
    @Id // JPA annotation to specify the primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO) // JPA annotation to specify that the primary key value will be generated automatically
    private Long id; // Unique identifier for the movie

    @NotNull // Validation annotation to specify that this field cannot be null
    @JsonProperty("title") // Jackson annotation to specify the JSON property name
    private String title;

    @NotNull // Validation annotation to specify that this field cannot be null
    @Min(1888) // Validation annotation to specify the minimum value for the release year (first movie was made in 1888)
    @Max(2025) // Validation annotation to specify the maximum value for the release year (current year)
    private int releaseYear;

    @NotNull // Validation annotation to specify that this field cannot be null
    @Enumerated(EnumType.STRING) // JPA annotation to specify that this field is an enum and should be stored as a string in the database

    @JsonProperty("genre") // Jackson annotation to specify the JSON property name
    @NotNull(message = "Genre cannot be null") // Validation annotation to specify that this field cannot be null
    private MovieGenre genre; // Enum to represent the genre of the movie
    private boolean isRented;

    @OneToMany(mappedBy = "movie" , cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Rating> ratings;
    

    public Movie(String title, int releaseYear, MovieGenre genre) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.isRented = false; // Default value for isRented
    }
    
}