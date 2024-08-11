package com.example.demo.dominan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =50)
    private String name;
    @Column(length =50)
    private String address;
    private Integer rating;
    private BigDecimal price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL, // te borra todo
            fetch = FetchType.EAGER, // carga los join todos datos completos lazy como una consulta sql noramal
            orphanRemoval = true, // si hay uno sin relacion te lo borra
            mappedBy = "hotel"
    )
    private Set<Reservation> reservation;
}
