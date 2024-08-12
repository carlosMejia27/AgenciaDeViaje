package com.example.demo.infraestructuras.abstract_service;

import com.example.demo.api.models.request.TourResquest;
import com.example.demo.api.models.response.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourResquest, TourResponse,Long>{

    void deleteTicket(UUID ticketId,Long tourId);
    UUID addTicket(Long flyId,Long tourId);

    void removeReservation(UUID ReservationId,Long tourId);
    UUID addReservation(Long ReservationId,Long tourId);
}
