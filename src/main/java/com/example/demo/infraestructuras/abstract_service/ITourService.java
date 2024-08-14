package com.example.demo.infraestructuras.abstract_service;

import com.example.demo.api.models.request.TourResquest;
import com.example.demo.api.models.response.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourResquest, TourResponse,Long>{

    void deleteTicket(Long tourId,UUID ticketId);
    UUID addTicket(Long flyId,Long tourId);


    public void removeReservation( Long tourId ,UUID ReservationId);


    UUID addReservation(Long tourId,Long hotelId ,Integer totalDays);
}
