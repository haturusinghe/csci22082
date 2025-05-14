package com.example.blockbusterapiv3.service;

import com.example.blockbusterapiv3.model.Rating;
import com.example.blockbusterapiv3.repository.RatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Object[]> getTopRatedMovies() {
        return ratingRepository.findMoviesByHighestAverageRating();
    }

    public List<Object[]> getAllMovieAverageRatings() {
        return ratingRepository.findMovieAverageRating();
    }

    public List<Object[]> getAllMovieRatingCounts() {
        return ratingRepository.findMoviesByMostRatings();
    }

    public List<Rating> getAllRatings() {
        return (List<Rating>) ratingRepository.findAll();
    }

    public Rating addRating(Rating rating) {
        ratingRepository.save(rating);
        return rating;
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    public Rating updateRating(Long id, Rating rating) {
        Optional<Rating> existingRating = ratingRepository.findById(id);
        if (existingRating.isPresent()) {
            Rating updatedRating = existingRating.get();
            updatedRating.setRating(rating.getRating());
            updatedRating.setMovie(rating.getMovie());
            updatedRating.setCustomer(rating.getCustomer());
            return ratingRepository.save(updatedRating);
        } else {
            throw new EntityNotFoundException("Rating not found with id: " + id);
        }
    }
}
