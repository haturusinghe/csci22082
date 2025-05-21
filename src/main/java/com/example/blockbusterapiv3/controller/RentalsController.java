package com.example.blockbusterapiv3.controller;

import com.example.blockbusterapiv3.model.Rental;
import com.example.blockbusterapiv3.model.RentalStatus;
import com.example.blockbusterapiv3.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
    private final RentalService rentalService;

    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @PostMapping("/rent")
    public ResponseEntity<Rental> rentMovie(@RequestBody Map<String, Object> payload) {
        Long customerId = Long.valueOf(payload.get("customerId").toString());
        Long movieId = Long.valueOf(payload.get("movieId").toString());
        int daysRented = Integer.parseInt(payload.get("daysRented").toString());
        Rental rental = rentalService.createRental(customerId, movieId, daysRented);
        return ResponseEntity.ok(rental);
    }

    @PostMapping("/return/{rentalId}")
    public ResponseEntity<Rental> returnMovie(@PathVariable Long rentalId) {
        Rental rental = rentalService.returnRental(rentalId);
        return ResponseEntity.ok(rental);
    }

    @GetMapping("/customer/{customerId}")
    public List<Rental> getRentalsByCustomer(@PathVariable Long customerId) {
        return rentalService.getRentalsByCustomer(customerId);
    }

    @GetMapping("/movie/{movieId}")
    public List<Rental> getRentalsByMovie(@PathVariable Long movieId) {
        return rentalService.getRentalsByMovie(movieId);
    }

    @GetMapping("/daterange")
    public List<Rental> getRentalsByDateRange(@RequestParam("start") String start,
                                              @RequestParam("end") String end) {
        return rentalService.getRentalsByDateRange(LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/count")
    public long getTotalRentalCount() {
        return rentalService.getTotalRentalCount();
    }

    @GetMapping("/count/status/{status}")
    public long getRentalCountByStatus(@PathVariable RentalStatus status) {
        return rentalService.getRentalCountByStatus(status);
    }

    @GetMapping("/count/customer/{customerId}")
    public long getRentalCountByCustomer(@PathVariable Long customerId) {
        return rentalService.getRentalCountByCustomer(customerId);
    }

    @GetMapping("/average-duration")
    public Double getAverageRentalDuration() {
        return rentalService.getAverageRentalDuration();
    }

    @GetMapping("/count/grouped-by-status")
    public List<Object[]> getRentalCountGroupedByStatus() {
        return rentalService.getRentalCountGroupedByStatus();
    }

    @GetMapping("/count/per-movie")
    public List<Object[]> getRentalCountPerMovie() {
        return rentalService.getRentalCountPerMovie();
    }

    @GetMapping("/active")
    public List<Rental> getCurrentlyRentedMovies() {
        return rentalService.getRentalsByStatus(RentalStatus.ACTIVE);
    }

}
