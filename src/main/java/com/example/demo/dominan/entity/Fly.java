package com.example.demo.dominan.entity;

import com.example.demo.util.Aerolinia;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Fly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double originLat ;
    private Double originLng ;
    private Double destinyLng ;
    private Double destinyLat ;
    private BigDecimal price;

    @Column(length =20)
    private String originName;

    @Column(length =20)
    private String destinyName;

    @Enumerated(EnumType.STRING)
    private Aerolinia aeroLine;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL, // te borra todo
            fetch = FetchType.EAGER, // carga los join todos datos completos lazy como una consulta sql noramal
            orphanRemoval = true, // si hay uno sin relacion te lo borra
            mappedBy = "fly"
    )
    private Set<Ticket> tickets;


}
