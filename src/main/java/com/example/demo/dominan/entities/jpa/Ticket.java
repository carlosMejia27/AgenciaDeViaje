package com.example.demo.dominan.entities.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ticket {

    @Id
    private UUID id;

    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private LocalDate purchaseDate;
    private BigDecimal price;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="fly_id")
    private Fly fly;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="tour_id",nullable = true)
    private Tour tour;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

}
