package com.example.blockbusterapiv3.service;

import com.example.blockbusterapiv3.model.Movie;
import com.example.blockbusterapiv3.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Service class for managing movies.
 * This class contains methods to perform CRUD operations on movies and to find movies by different criteria.
 */
@Service // Spring annotation to indicate that this class is a service
public class MovieService {
    private final MovieRepository movieRepository; // Repository for performing CRUD operations on movies

    // Constructor injection of the MovieRepository
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository; // Assigning the injected repository to the class variable
    }
    

    /**
     * Retrieves all movies from the repository.
     *
     * This method interacts with the movieRepository instance to fetch all movie records
     * stored in the underlying datastore and returns them as a list.
     *
     * @return a list containing all the movie objects
     */
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Movie> findMoviesByReleaseYearRange(int startYear, int endYear) {
        return movieRepository.findByReleaseYearBetween(startYear, endYear);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void addMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
    
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }


    @Transactional
    // Spring annotation to indicate that this method should be executed within a transaction, because it modifies the database
    // This is important for ensuring data integrity and consistency, especially when multiple operations are performed in a single method. This ensures that if any operation fails, the entire transaction can be rolled back to maintain the state of the database.
    public Movie updateMovieDetails(Long id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        if (existingMovie != null) {
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setReleaseYear(movie.getReleaseYear());
            existingMovie.setGenre(movie.getGenre());
            return movieRepository.save(existingMovie);
        }
        return null;
    }
}