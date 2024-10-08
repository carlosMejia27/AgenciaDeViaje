package com.example.demo.dominan.entities.jpa;

import com.example.demo.dominan.entities.jpa.Customer;
import com.example.demo.dominan.entities.jpa.Reservation;
import com.example.demo.dominan.entities.jpa.Ticket;
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



    @PrePersist
    @PreRemove
    public void updateFK(){
        this.tickets.forEach(tickets->tickets.setTour(this));
        this.reservation.forEach(reservation->reservation.setTour(this));

    }

    public void addTicket( Ticket ticket){
        if(Objects.isNull(this.tickets)) this.tickets=new HashSet<>();
        this.tickets.add(ticket);
        this.tickets.forEach(t->t.setTour(this));

    }


    public void removeTicket(UUID id){
           this.tickets.forEach(ticket -> {
               if (ticket.getId().equals(id)){
                   ticket.setTour(null);
               }
           });
    }


    public void addReservation( Reservation reservation){
        if(Objects.isNull(this.reservation)) this.reservation=new HashSet<>();
        this.reservation.add(reservation);
        this.reservation.forEach(r->r.setTour(this));

    }

    public void removeReservation(UUID id){
        this.reservation.forEach(reservation -> {
            if (reservation.getId().equals(id)){
                reservation.setTour(null);
            }
        });
    }





//
//    public void addTicket( Ticket ticket){
//        if(Objects.isNull(this.tickets)) this.tickets=new HashSet<>();
//        this.tickets.add(ticket);
//
//    }
//
//
//
//    public void removeTicket( UUID id){
//        if(Objects.isNull(this.tickets)) this.tickets=new HashSet<>();
//        this.tickets.removeIf(ticket->ticket.getId().equals(id));
//
//    }
//
//    public void updateTicket( ){
//        this.tickets.forEach(tickets->tickets.setTour(this));
//
//    }
//
//    // motodos para seteaar Reservation
//
//    public void addReservation( Reservation reservation){
//        if(Objects.isNull(this.reservation)) this.reservation=new HashSet<>();
//        this.reservation.add(reservation);
//
//    }
//
//    public void removeReservation( UUID idReservacion){
//        if(Objects.isNull(this.reservation)) this.reservation=new HashSet<>();
//        this.reservation.removeIf(reservation->reservation.getId().equals(idReservacion));
//
//    }
//
//    public void updateReservation( ){
//        this.reservation.forEach(r->r.setTour(this));
//
//    }
}
