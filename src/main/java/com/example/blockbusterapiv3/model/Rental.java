package com.example.blockbusterapiv3.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDate rentedDate;

    private LocalDate returnedDate;

    @NotNull
    @Min(1)
    @Max(14)
    private int numDaysRented;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    public Rental(Customer customer, Movie movie, LocalDate rentedDate, int numDaysRented) {
        this.customer = customer;
        this.movie = movie;
        this.rentedDate = rentedDate;
        this.numDaysRented = numDaysRented;
    }

    public Rental(Customer customer, Movie movie, int numDaysRented) {
        this.customer = customer;
        this.movie = movie;
        this.numDaysRented = numDaysRented;
        this.rentedDate = java.time.LocalDate.now();
    }

    @PrePersist
    public void onCreate(){
        if(this.rentedDate == null){
            this.rentedDate = LocalDate.now();
        }

        if(this.returnedDate == null){
            if (LocalDate.now().isAfter(this.rentedDate.plusDays(this.numDaysRented))){
                this.rentalStatus = RentalStatus.OVERDUE;
            }else {
                this.rentalStatus = RentalStatus.ACTIVE;
            }
        }else {
            if (this.returnedDate.isAfter(this.rentedDate.plusDays(this.numDaysRented))){
                this.rentalStatus = RentalStatus.PENALTIES;
            }else {
                this.rentalStatus = RentalStatus.RETURNED;
            }
        }
    }

}
