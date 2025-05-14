package com.example.blockbusterapiv3.repository;

import com.example.blockbusterapiv3.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    List<Customer> findByRegistrationDateBetween(LocalDate start, LocalDate end);

    // Custom JPQL query: Find customers registered after a certain date
    @Query("SELECT c FROM customers c WHERE c.registrationDate > :date")
    List<Customer> findCustomersRegisteredAfter(@Param("date") LocalDate date);

    List<Customer> findByAddress_CityIgnoreCase(String city);

    //Query to find Customers count by City
    @Query("SELECT a.city, COUNT(c) FROM customers c JOIN c.address a GROUP BY a.city")
    List<Object[]> CustomerCountPerCity();
}
