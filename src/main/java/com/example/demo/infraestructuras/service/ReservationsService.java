package com.example.demo.infraestructuras.service;

import com.example.demo.api.models.request.ReservationRequest;
import com.example.demo.api.models.response.HotelResponde;
import com.example.demo.api.models.response.ReservationResponse;
import com.example.demo.dominan.entity.Reservation;
import com.example.demo.dominan.repository.CustomerRepository;
import com.example.demo.dominan.repository.HotelRepository;
import com.example.demo.dominan.repository.ReservacionRepository;
import com.example.demo.infraestructuras.abstract_service.IreservationsService;
import com.example.demo.infraestructuras.helpers.ApiCurrenceConnectorHelper;
import com.example.demo.infraestructuras.helpers.BlackListHelpers;
import com.example.demo.infraestructuras.helpers.CustomerHelper;
import com.example.demo.util.Best_Travel_Util;
import com.example.demo.util.enunm.Tables;
import com.example.demo.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationsService implements IreservationsService {

    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final ReservacionRepository reservacionRepository;
    private final CustomerHelper customerHelper;
    private BlackListHelpers blackListHelpers;
    private final ApiCurrenceConnectorHelper apiCurrenceConnectorHelper;

    public static final BigDecimal charge_price_percentage = BigDecimal.valueOf(0.20);


    @Override
    public ReservationResponse create(ReservationRequest request) {
        blackListHelpers.isInBlackListCustomer(request.getIdClient());
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow(() -> new IdNotFoundException("customer"));
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow(() -> new IdNotFoundException("hotel"));
        var reservation = Reservation.builder()
                .id(UUID.randomUUID())
                .dateReservation(Best_Travel_Util.getRandomSoon())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .totalDays(request.getTotalDays())
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charge_price_percentage)))
                .hotel(hotel)
                .customer(customer)
                .build();

        var reservationUpdated = this.reservacionRepository.save(reservation);
        this.customerHelper.incrase(customer.getDni(), ReservationsService.class);
        return this.ReservetionToResponse(reservationUpdated);
    }

    @Override
    public ReservationResponse read(UUID uuid) {
        var ReservationFromBD = this.reservacionRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException("reservation"));
        return this.ReservetionToResponse(ReservationFromBD);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow(() -> new IdNotFoundException("hotel"));

        var reservationToUpdate = this.reservacionRepository.findById(id).orElseThrow(() -> new IdNotFoundException("reservations"));
        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setDateReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charge_price_percentage)));

        var reservationUpdated = this.reservacionRepository.save(reservationToUpdate);

        return this.ReservetionToResponse(reservationUpdated);

    }

    @Override
    public void delete(UUID uuid) {
        var ReservationTodelete = reservacionRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException("reservation"));
        this.reservacionRepository.delete(ReservationTodelete);

    }


    private ReservationResponse ReservetionToResponse(Reservation entity) {
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);

        var hotelResponde = new HotelResponde();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponde);

        response.setHotel(hotelResponde);
        return response;
    }


    @Override
    public BigDecimal findPrice(Long hotelId, Currency currency) {
        var hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
        var priceInDolares = hotel.getPrice().add(hotel.getPrice().multiply(charge_price_percentage));
        if (currency.equals(Currency.getInstance("USD"))) return priceInDolares;
        var currencyDTO=this.apiCurrenceConnectorHelper.getCurrency(currency);
        log.info("Api currency in {}, ______*************************_____response {}",currencyDTO.getExchangedate().toString(),currencyDTO.getRates());
        return priceInDolares.multiply(currencyDTO.getRates().get(currency));

    }
}
