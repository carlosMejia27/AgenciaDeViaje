package com.example.demo.infraestructuras.service;

import com.example.demo.api.models.request.TourResquest;
import com.example.demo.api.models.response.TourResponse;
import com.example.demo.dominan.entity.*;
import com.example.demo.dominan.repository.*;
import com.example.demo.infraestructuras.abstract_service.ITourService;
import com.example.demo.infraestructuras.helpers.TourHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
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

    @Override
    public TourResponse create(TourResquest request) {

        var customer = customerRepository.findById(request.getCustumerId()).orElseThrow();

        var flights=new HashSet<Fly>();
        request.getFlights().forEach(fly->flights.add(this.flyRepository.findById(fly.getId()).orElseThrow()));
        var hotels=new HashMap<Hotel,Integer>();
        request.getHotels().forEach(hotel->hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(),hotel.getTotalDays()));
        var tourToSave= Tour.builder()
                .tickets(this.tourHelper.createTickets(flights,customer))
                .reservation(this.tourHelper.createReservation(hotels,customer))
                .customer(customer)
                .build();

        var tourSaved=this.tourRepository.save(tourToSave);
        return TourResponse.builder()
                .reservationIds(tourToSave.getReservation().stream().map(Reservation::getId).collect(Collectors.toSet()))
                .ticketIds(tourSaved.getTickets().stream().map(Ticket::getId).collect(Collectors.toSet()))
                .id(tourSaved.getId())
                .build();

    }

    @Override
    public TourResponse read(Long id) {

        var tourFromDb=this.tourRepository.findById(id).orElseThrow();

        log.info("***********iiiiiiiiiiiDDDDDD**********"+ tourFromDb);

        return TourResponse.builder()
                .reservationIds(tourFromDb.getReservation().stream().map(Reservation::getId).collect(Collectors.toSet()))
                .ticketIds(tourFromDb.getTickets().stream().map(Ticket::getId).collect(Collectors.toSet()))
                .id(tourFromDb.getId())
                .build();
    }

    @Override
    public void delete(Long id) {
      var tourToDelete=this.tourRepository.findById(id).orElseThrow();
      this.tourRepository.delete(tourToDelete);
    }

    @Override
    public void deleteTicket(UUID ticketId, Long tourId) {

    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        return null;
    }

    @Override
    public void removeReservation(UUID ReservationId, Long tourId) {

    }

    @Override
    public UUID addReservation(Long ReservationId, Long tourId) {
        return null;
    }
}
