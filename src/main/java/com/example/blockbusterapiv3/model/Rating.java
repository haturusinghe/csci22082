package com.example.blockbusterapiv3.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(1)
    @Max(10)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonManagedReference
    private Movie movie;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Rating(int rating, Movie movie, Customer customer) {
        this.rating = rating;
        this.movie = movie;
        this.customer = customer;
    }
}
