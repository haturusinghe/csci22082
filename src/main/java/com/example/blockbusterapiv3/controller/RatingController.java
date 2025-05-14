package com.example.blockbusterapiv3.controller;

import com.example.blockbusterapiv3.model.Rating;
import com.example.blockbusterapiv3.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/top")
    public List<Object[]> getTopRatedMovies() {
        return ratingService.getTopRatedMovies();
    }

    @GetMapping("/averages")
    public List<Object[]> getAllMovieAverageRatings() {
        return ratingService.getAllMovieAverageRatings();
    }

    @GetMapping("/most-rated")
    public List<Object[]> getAllMovieRatingCounts() {
        return ratingService.getAllMovieRatingCounts();
    }

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @PostMapping
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }
}
