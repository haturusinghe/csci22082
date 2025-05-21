package com.example.blockbusterapiv3.service;

import com.example.blockbusterapiv3.model.Customer;
import com.example.blockbusterapiv3.model.Movie;
import com.example.blockbusterapiv3.model.Rental;
import com.example.blockbusterapiv3.model.RentalStatus;
import com.example.blockbusterapiv3.repository.MovieRepository;
import com.example.blockbusterapiv3.repository.RentalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final CustomerService customerService;

    public RentalService(RentalRepository rentalRepository, MovieRepository movieRepository, MovieService movieService, CustomerService customerService) {
        this.rentalRepository = rentalRepository;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.customerService = customerService;
    }

    public List<Rental> getAllRentals(){
        return (List<Rental>) rentalRepository.findAll();
    }

    public Rental addRental(Rental rental){
        return rentalRepository.save(rental);
    }

    public Rental createRental(Movie movie, Customer customer, int numDaysRented){
        if(movie.isRented()){
            throw new IllegalArgumentException("Movie is already rented!");
        }

        Rental rental = new Rental(customer,movie, numDaysRented);
        movie.setRented(true);
        movieRepository.save(movie);

        return rentalRepository.save(rental);
    }

    @Transactional
    public Rental returnRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found!"));
        if (rental.getRentalStatus() == RentalStatus.RETURNED) {
            throw new IllegalStateException("Rental already returned.");
        }
        rental.setReturnedDate(LocalDate.now());
        // Update status based on return timing
        if (rental.getReturnedDate().isAfter(rental.getRentedDate().plusDays(rental.getNumDaysRented()))) {
            rental.setRentalStatus(RentalStatus.PENALTIES);
        } else {
            rental.setRentalStatus(RentalStatus.RETURNED);
        }
        Movie movie = rental.getMovie();
        movie.setRented(false);
        movieRepository.save(movie);
        return rentalRepository.save(rental);
    }

    @Transactional
    public Rental createRental(Long movieId, Long customerId, int daysRented) {
        Movie movie = movieService.getMovieById(movieId);
        Customer customer = customerService.getCustomerById(customerId).orElse(null);

        //check if movie and customer exist
        if (movie == null) {
            throw new IllegalArgumentException("Movie not found!");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found!");
        }

        Rental rental = new Rental(customer, movie, daysRented);
        movie.setRented(true);
        movieRepository.save(movie);

        return rentalRepository.save(rental);

    }

    public List<Rental> getRentalsByCustomer(Long customerId) {
        return rentalRepository.findByCustomerId(customerId);
    }

    public List<Rental> getRentalsByStatus(RentalStatus status) {
        return rentalRepository.findByRentalStatus(status);
    }

    public List<Rental> getRentalsByMovie(Long movieId) {
        return rentalRepository.findByMovieId(movieId);
    }

    public List<Rental> getRentalsByDateRange(LocalDate start, LocalDate end) {
        return rentalRepository.findByRentedDateBetween(start, end);
    }

    public long getTotalRentalCount() {
        return rentalRepository.count();
    }

    public long getRentalCountByStatus(RentalStatus status) {
        return rentalRepository.countByRentalStatus(status);
    }

    public long getRentalCountByCustomer(Long customerId) {
        return rentalRepository.countByCustomerId(customerId);
    }

    public Double getAverageRentalDuration() {
        return rentalRepository.findAverageRentalDuration();
    }

    public List<Object[]> getRentalCountGroupedByStatus() {
        return rentalRepository.countRentalsGroupedByStatus();
    }

    public List<Object[]> getRentalCountPerMovie() {
        return rentalRepository.countRentalsPerMovie();
    }
}
