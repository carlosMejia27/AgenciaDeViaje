package com.example.demo.dominan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ticket {

    @Id
    private UUID id;

    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalDate purchaseDate;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name="fly_id")
    private Fly fly;

    @ManyToOne
    @JoinColumn(name="tour_id",nullable = true)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

}
