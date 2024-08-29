package com.example.demo.infraestructuras.helpers;

import com.example.demo.dominan.entities.jpa.*;
import com.example.demo.dominan.repository.jpa.ReservacionRepository;
import com.example.demo.dominan.repository.jpa.TicketRepository;
import com.example.demo.infraestructuras.service.ReservationsService;
import com.example.demo.infraestructuras.service.TicketService;
import com.example.demo.util.Best_Travel_Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {

    private final TicketRepository ticketRepository;
    private final ReservacionRepository reservacionRepository;

    public Set<Ticket> createTickets(Set<Fly> flights, Customer customer){
        var respose=new HashSet<Ticket>(flights.size());
        flights.forEach(fly->{

            var ticketToPersist = Ticket.builder()
                    .id(UUID.randomUUID())
                    .fly(fly)
                    .customer(customer)
                    .price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charge_price_percentage)))
                    .purchaseDate(LocalDate.now())
                    .arrivalDate(Best_Travel_Util.getRandomLater())
                    .departureDate(Best_Travel_Util.getRandomSoon())
                    .build();
            respose.add(this.ticketRepository.save(ticketToPersist));
        });
       return respose;
    }


    public Set<Reservation> createReservation(HashMap<Hotel,Integer> hotels, Customer customer){
        var respose=new HashSet<Reservation>(hotels.size());
        hotels.forEach((hotel ,totaDays)->{
            var reservation= Reservation.builder()
                    .id(UUID.randomUUID())
                    .dateReservation(Best_Travel_Util.getRandomSoon())
                    .dateStart(LocalDate.now())
                    .dateEnd(LocalDate.now().plusDays(totaDays))
                    .totalDays(totaDays)
                    .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationsService.charge_price_percentage)))
                    .hotel(hotel)
                    .customer(customer)
                    .build();
            respose.add(this.reservacionRepository.save(reservation));

        });
        return respose;

    }

    public Ticket createTicket(Fly fly ,Customer customer){
        var ticketPersist = Ticket.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charge_price_percentage)))
                .purchaseDate(LocalDate.now())
                .arrivalDate(Best_Travel_Util.getRandomLater())
                .departureDate(Best_Travel_Util.getRandomSoon())
                .build();
        return  this.ticketRepository.save(ticketPersist);
    }

    public Reservation createReaervation(Hotel hotel , Customer customer ,Integer totaldays){
        var reservationToPersist= Reservation.builder()
                .id(UUID.randomUUID())
                .dateReservation(Best_Travel_Util.getRandomSoon())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(totaldays))
                .totalDays(totaldays)
                .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationsService.charge_price_percentage)))
                .hotel(hotel)
                .customer(customer)
                .build();
        return this.reservacionRepository.save(reservationToPersist);
    }
}
