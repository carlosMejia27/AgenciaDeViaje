package com.example.demo.infraestructuras.service;

import com.example.demo.api.models.request.TourResquest;
import com.example.demo.api.models.response.TourResponse;
import com.example.demo.dominan.entities.jpa.*;
import com.example.demo.dominan.repository.jpa.CustomerRepository;
import com.example.demo.dominan.repository.jpa.FlyRepository;
import com.example.demo.dominan.repository.jpa.HotelRepository;
import com.example.demo.dominan.repository.jpa.TourRepository;
import com.example.demo.infraestructuras.abstract_service.ITourService;
import com.example.demo.infraestructuras.helpers.BlackListHelpers;
import com.example.demo.infraestructuras.helpers.CustomerHelper;
import com.example.demo.infraestructuras.helpers.EmailHealpers;
import com.example.demo.infraestructuras.helpers.TourHelper;
import com.example.demo.util.enunm.Tables;
import com.example.demo.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TourService implements ITourService {

    private final TourRepository tourRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final TourHelper tourHelper;
    private final CustomerHelper customerHelper;
    private BlackListHelpers blackListHelpers;
    private final EmailHealpers mailHealpers;


    @Override
    public TourResponse create(TourResquest request) {
        blackListHelpers.isInBlackListCustomer(request.getCustumerId());
        var customer = customerRepository.findById(request.getCustumerId()).orElseThrow(()-> new IdNotFoundException(Tables.customer.name()));

        var flights=new HashSet<Fly>();
        request.getFlights().forEach(fly->
                flights.add(this.flyRepository.findById(fly.getId())
                        .orElseThrow(()-> new IdNotFoundException(Tables.fly.name()))));


        var hotels=new HashMap<Hotel,Integer>();
        request.getHotels().forEach(hotel->hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(),hotel.getTotalDays()));
        var tourToSave= Tour.builder()
                .tickets(this.tourHelper.createTickets(flights,customer))
                .reservation(this.tourHelper.createReservation(hotels,customer))
                .customer(customer)
                .build();

        var tourSaved=this.tourRepository.save(tourToSave);

        this.customerHelper.incrase(customer.getDni(),TourService.class);
        if(Objects.isNull(request.getEmail())) this.mailHealpers.sendmail(request.getEmail(),customer.getFullName(), Tables.tour.name());

        return TourResponse.builder()
                .reservationIds(tourToSave.getReservation().stream().map(Reservation::getId).collect(Collectors.toSet()))
                .ticketIds(tourSaved.getTickets().stream().map(Ticket::getId).collect(Collectors.toSet()))
                .id(tourSaved.getId())
                .build();

    }

    @Override
    public TourResponse read(Long id) {

        var tourFromDb=this.tourRepository.findById(id).orElseThrow();

        return TourResponse.builder()
                .reservationIds(tourFromDb.getReservation().stream().map(Reservation::getId).collect(Collectors.toSet()))
                .ticketIds(tourFromDb.getTickets().stream().map(Ticket::getId).collect(Collectors.toSet()))
                .id(tourFromDb.getId())
                .build();
    }

    @Override
    public void delete(Long id) {
      var tourToDelete=this.tourRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.tour.name()));
        log.info("*************tour id no existe *************** [{}]",tourToDelete);
        this.tourRepository.delete(tourToDelete);
    }



    @Override
    public UUID addTicket(Long flyId, Long tourId) {

        var tourUpdate=this.tourRepository.findById(tourId).orElseThrow();
        log.info("*************IDTour*************** [{}]",tourUpdate);
        var fly =this.flyRepository.findById(flyId).orElseThrow();
        var ticket =tourHelper.createTicket(fly,tourUpdate.getCustomer());
        tourUpdate.addTicket(ticket);
        this.tourRepository.save(tourUpdate);
        return ticket.getId();

    }

    @Override
    public void deleteTicket(Long tourId,UUID ticketId) {
      var tourUpdate=this.tourRepository.findById(tourId).orElseThrow();
        tourUpdate.removeTicket(ticketId);
        this.tourRepository.save(tourUpdate);
    }


    @Override
    public void removeReservation( Long tourId ,UUID reservationId) {
        var reservationUpdate=this.tourRepository.findById(tourId).orElseThrow();
        reservationUpdate.removeReservation(reservationId);
        this.tourRepository.save(reservationUpdate);
    }

    @Override
    public UUID addReservation(Long tourId,Long hotelId ,Integer totalDays) {

        var reservationUpdate=this.tourRepository.findById(tourId).orElseThrow();
        var hotel =this.hotelRepository.findById(hotelId).orElseThrow();
        var reservations =tourHelper.createReaervation(hotel,reservationUpdate.getCustomer(),totalDays);
        reservationUpdate.addReservation(reservations);
        this.tourRepository.save(reservationUpdate);

        return reservations.getId();
    }
}
