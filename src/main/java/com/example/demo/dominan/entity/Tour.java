package com.example.demo.dominan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
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
        if(Objects.isNull(this.tickets)) this.tickets=new HashSet<>();
        this.tickets.add(ticket);

    }

    public void removeTicket( UUID id){
        if(Objects.isNull(this.tickets)) this.tickets=new HashSet<>();
        this.tickets.removeIf(ticket->ticket.getId().equals(id));

    }

    public void updateTicket( ){
        this.tickets.forEach(tickets->tickets.setTour(this));

    }

    // motodos para seteaar Reservation

    public void addReservation( Reservation reservation){
        if(Objects.isNull(this.reservation)) this.reservation=new HashSet<>();
        this.reservation.add(reservation);

    }

    public void removeReservation( UUID idReservacion){
        if(Objects.isNull(this.reservation)) this.reservation=new HashSet<>();
        this.reservation.removeIf(reservation->reservation.getId().equals(idReservacion));

    }

    public void updateReservation( ){
        this.reservation.forEach(r->r.setTour(this));

    }
}
