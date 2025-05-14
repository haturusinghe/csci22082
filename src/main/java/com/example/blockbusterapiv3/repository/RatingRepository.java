package com.example.blockbusterapiv3.repository;

import com.example.blockbusterapiv3.model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating,Long> {

    @Query("SELECT r.movie, AVG(r.rating) as avgRating FROM ratings r GROUP BY r.movie ORDER BY avgRating DESC LIMIT 5")
    List<Object[]> findMoviesByHighestAverageRating();

    @Query("SELECT r.movie.title, AVG(r.rating) as avgRating FROM ratings r GROUP BY r.movie")
    List<Object[]> findMovieAverageRating();

    @Query("SELECT r.movie.title, COUNT(r) as ratingCount FROM ratings r GROUP BY r.movie.title ORDER BY ratingCount DESC")
    List<Object[]> findMoviesByMostRatings();


}
