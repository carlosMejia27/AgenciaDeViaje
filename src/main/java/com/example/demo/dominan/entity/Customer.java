package com.example.demo.dominan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {

    @Id
    @Column(length =20)
    private String dni;
    @Column(length =50)
    private String fullName;
    @Column(length =20)
    private String creditCard;

    @Column(length =12)
    private String phoneNumber;

    private Integer totalFlights;
    private Integer totalLodgings;
    private Integer totalTours;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL, // te borra todo
            fetch = FetchType.EAGER, // carga los join todos datos completos lazy como una consulta sql noramal
            orphanRemoval = true, // si hay uno sin relacion te lo borra
            mappedBy = "customer"
    )
    private Set<Ticket> ticket;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL, // te borra todo
            fetch = FetchType.EAGER, // carga los join todos datos completos lazy como una consulta sql noramal
            orphanRemoval = true, // si hay uno sin relacion te lo borra
            mappedBy = "customer"
    )
    private Set<Reservation> reservation;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL, // te borra todo
            fetch = FetchType.EAGER, // carga los join todos datos completos lazy como una consulta sql noramal
            orphanRemoval = true, // si hay uno sin relacion te lo borra
            mappedBy = "customer"
    )
    private Set<Tour> tour;







}
