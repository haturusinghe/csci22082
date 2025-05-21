package com.example.blockbusterapiv3.utility;/* Dataloader class to populate the database with initial movie data. This class will be responsible for loading predefined movie records into the database upon application startup. */

import com.example.blockbusterapiv3.model.*;
import com.example.blockbusterapiv3.service.CustomerService;
import com.example.blockbusterapiv3.service.MovieService;
import com.example.blockbusterapiv3.service.RatingService;
import com.example.blockbusterapiv3.service.RentalService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component // Spring annotation to indicate that this class is a component and should be managed by the Spring container
public class DataLoader{
    private final MovieService movieService; // Service for performing CRUD operations on movies
    private final CustomerService customerService;
    private final RatingService ratingService;
    private final RentalService rentingService;

    public DataLoader(MovieService movieService, CustomerService customerService, RatingService ratingService, RentalService rentingService) {
        this.movieService = movieService; // Assigning the injected service to the class variable
        this.customerService = customerService;
        this.ratingService = ratingService;
        this.rentingService = rentingService;
    }

    @PostConstruct
    // Spring annotation to indicate that this method should be executed after the bean is constructed and all dependencies are injected
    public void loadData() {
        // Check if the database is empty before loading data
        if (movieService.getAllMovies().isEmpty()) {
            // Create a list of movies to be added to the database
            List<Movie> movies = new ArrayList<>();
            movies.add(new Movie("The Shawshank Redemption", 1994, MovieGenre.DRAMA));
            movies.add(new Movie("The Godfather", 1972, MovieGenre.DRAMA));
            movies.add(new Movie("The Dark Knight", 2008, MovieGenre.ACTION));
            movies.add(new Movie("Pulp Fiction", 1994, MovieGenre.ACTION));
            movies.add(new Movie("Forrest Gump", 1994, MovieGenre.DRAMA));
            movies.add(new Movie("Inception", 2010, MovieGenre.ACTION));
            movies.add(new Movie("Fight Club", 1999, MovieGenre.DRAMA));
            movies.add(new Movie("The Matrix", 1999, MovieGenre.ACTION));
            movies.add(new Movie("The Lord of the Rings: The Return of the King", 2003, MovieGenre.ACTION));
            movies.add(new Movie("Interstellar", 2014, MovieGenre.SCI_FI));

            // Add the list of movies to the database
            movieService.addMovies(movies);
        }

        // add customers
        // Check if the database is empty before loading data
        if (customerService.getAllCustomers().isEmpty()) {

            List<Customer> customers = new ArrayList<>();

            Customer john = new Customer("John", "Doe", "john.doe@example.com");
            john.setRegistrationDate(LocalDate.of(2021, 1, 15));
            john.setAddress(new Address("Colombo", "12345"));
            customers.add(john);

            Customer jane = new Customer("Jane", "Smith", "jane.smith@example.com");
            jane.setRegistrationDate(LocalDate.of(2020, 5, 20));
            jane.setAddress(new Address("Kandy", "54321"));
            customers.add(jane);

            Customer alice = new Customer("Alice", "Johnson", "alice.johnson@example.com");
            alice.setRegistrationDate(LocalDate.of(2019, 3, 10));
            alice.setAddress(new Address("Galle", "67890"));
            customers.add(alice);

            Customer bob = new Customer("Bob", "Brown", "bob.brown@example.com");
            bob.setRegistrationDate(LocalDate.of(2022, 7, 25));
            bob.setAddress(new Address("Colombo", "12345"));
            customers.add(bob);

            customerService.saveAllCustomers(customers);
        }

        // Add sample ratings if none exist
        if (ratingService.getAllRatings().isEmpty()) {
            List<Customer> customers = customerService.getAllCustomers();
            List<Movie> movies = movieService.getAllMovies();
            if (!customers.isEmpty() && !movies.isEmpty()) {
                Customer john = customers.stream().filter(c -> c.getEmail().equals("john.doe@example.com")).findFirst().orElse(null);
                Customer jane = customers.stream().filter(c -> c.getEmail().equals("jane.smith@example.com")).findFirst().orElse(null);
                Customer alice = customers.stream().filter(c -> c.getEmail().equals("alice.johnson@example.com")).findFirst().orElse(null);
                Customer bob = customers.stream().filter(c -> c.getEmail().equals("bob.brown@example.com")).findFirst().orElse(null);

                Movie shawshank = movies.stream().filter(m -> m.getTitle().equals("The Shawshank Redemption")).findFirst().orElse(null);
                Movie inception = movies.stream().filter(m -> m.getTitle().equals("Inception")).findFirst().orElse(null);
                Movie matrix = movies.stream().filter(m -> m.getTitle().equals("The Matrix")).findFirst().orElse(null);
                Movie godfather = movies.stream().filter(m -> m.getTitle().equals("The Godfather")).findFirst().orElse(null);

                if (john != null && shawshank != null) {
                    ratingService.addRating(new Rating( 9, shawshank, john));
                }
                if (jane != null && inception != null) {
                    ratingService.addRating(new Rating( 8, inception, jane));
                }
                if (alice != null && matrix != null) {
                    ratingService.addRating(new Rating( 7, matrix, alice));
                }
                if (bob != null && godfather != null) {
                    ratingService.addRating(new Rating(10, godfather, bob));
                }
                // Add more sample ratings as needed
            }
        }

        // Add sample rentals if none exist
        if (rentingService.getAllRentals().isEmpty()) {
            List<Customer> customers = customerService.getAllCustomers();
            List<Movie> movies = movieService.getAllMovies();
            if (!customers.isEmpty() && !movies.isEmpty()) {
                Customer john = customers.stream().filter(c -> c.getEmail().equals("john.doe@example.com")).findFirst().orElse(null);
                Customer jane = customers.stream().filter(c -> c.getEmail().equals("jane.smith@example.com")).findFirst().orElse(null);
                Customer alice = customers.stream().filter(c -> c.getEmail().equals("alice.johnson@example.com")).findFirst().orElse(null);
                Customer bob = customers.stream().filter(c -> c.getEmail().equals("bob.brown@example.com")).findFirst().orElse(null);

                Movie shawshank = movies.stream().filter(m -> m.getTitle().equals("The Shawshank Redemption")).findFirst().orElse(null);
                Movie inception = movies.stream().filter(m -> m.getTitle().equals("Inception")).findFirst().orElse(null);
                Movie matrix = movies.stream().filter(m -> m.getTitle().equals("The Matrix")).findFirst().orElse(null);
                Movie godfather = movies.stream().filter(m -> m.getTitle().equals("The Godfather")).findFirst().orElse(null);

                if (john != null && shawshank != null) {
                    rentingService.createRental(shawshank,john,7);
                }
                if (jane != null && inception != null) {
                    rentingService.createRental(inception,jane,7);
                }
                if (alice != null && matrix != null) {
                    rentingService.createRental(matrix,alice,14);
                }
                if (bob != null && godfather != null) {
                    rentingService.createRental(godfather,bob,3);
                }
                // Add more sample ratings as needed
            }
        }
    }
}