package com.example.blockbusterapiv3.controller;

import com.example.blockbusterapiv3.model.Movie;
import com.example.blockbusterapiv3.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Spring annotation to indicate that this class is a REST controller
@RequestMapping("/api/movies")
// Spring annotation to indicate that this class is a REST controller and handles HTTP requests for the "/api/movies" endpoint
public class MovieController{

    private final MovieService movieService; // Service for performing CRUD operations on movies

    // Constructor injection of the MovieService
    public MovieController(MovieService movieService) {
        this.movieService = movieService; // Assigning the injected service to the class variable
    }

    @GetMapping // Handles GET /api/movies
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies(); // Fetching all movies from the service
    }

    @GetMapping("/{id}") // Handles GET /api/movies/{id}
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id); // Fetching a movie by ID from the service
    }

    @GetMapping("/search") // Handles GET /api/movies/search?title=...
    public List<Movie> searchMoviesByTitle(@RequestParam String title) {
        return movieService.searchMoviesByTitle(title); // Searching for movies by title using the service
    }

    @GetMapping("/range") // Handles GET /api/movies/range?startYear=...&endYear=...
    public List<Movie> findMoviesByReleaseYearRange(@RequestParam int startYear, @RequestParam int endYear) {
        return movieService.findMoviesByReleaseYearRange(startYear, endYear); // Finding movies by release year range using the service
    }
    
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie); // Adding a new movie using the service
    }

    @PutMapping("/{id}") // Spring annotation to handle PUT requests for updating a specific movie by ID
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.updateMovieDetails(id, movie); // Updating a movie using the service
    }
    
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

}