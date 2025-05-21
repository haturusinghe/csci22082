package com.example.blockbusterapiv3.repository;

import com.example.blockbusterapiv3.model.Rental;
import com.example.blockbusterapiv3.model.RentalStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends CrudRepository<Rental,Long> {
    // Retrieval Queries
    List<Rental> findByCustomerId(Long customerId);
    List<Rental> findByRentalStatus(RentalStatus status);
    List<Rental> findByMovieId(Long movieId);
    List<Rental> findByRentedDateBetween(LocalDate start, LocalDate end);
    // Aggregation Queries
    long count();
    long countByRentalStatus(RentalStatus status);
    long countByCustomerId(Long customerId);

    @Query("SELECT AVG(r.numDaysRented) FROM rentals r")
    Double findAverageRentalDuration();

    @Query("SELECT r.rentalStatus, COUNT(r) FROM rentals r GROUP BY r.rentalStatus")
    List<Object[]> countRentalsGroupedByStatus();

    @Query("SELECT r.movie.id, COUNT(r) FROM rentals r GROUP BY r.movie.id")
    List<Object[]> countRentalsPerMovie();
}
