package com.example.demo.infraestructuras.abstract_service;

import com.example.demo.api.models.request.ReservationRequest;
import com.example.demo.api.models.response.ReservationResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface IreservationsService extends CrudService<ReservationRequest, ReservationResponse, UUID>{
    BigDecimal findPrice(Long hotelId);

}
