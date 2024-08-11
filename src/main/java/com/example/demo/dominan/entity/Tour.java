package com.example.demo.dominan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL, // te borra todo
            fetch = FetchType.EAGER, // carga los join todos datos completos lazy como una consulta sql noramal
            orphanRemoval = true, // si hay uno sin relacion te lo borra
            mappedBy = "tour"
    )
    private Set<Ticket> tickets;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL, // te borra todo
            fetch = FetchType.EAGER, // carga los join todos datos completos lazy como una consulta sql noramal
            orphanRemoval = true, // si hay uno sin relacion te lo borra
            mappedBy = "tour"
    )
    private Set<Reservation> reservation;

    @ManyToOne
    @JoinColumn(name="id_customer")
    private Customer customer;

    public void addTicket( Ticket ticket){
        this.tickets.add(ticket);

    }

    public void removeTicket( UUID id){
        this.tickets.removeIf(ticket->ticket.getId().equals(id));

    }

    public void updateTicket( ){
        this.tickets.forEach(tickets->tickets.setTour(this));

    }
}
