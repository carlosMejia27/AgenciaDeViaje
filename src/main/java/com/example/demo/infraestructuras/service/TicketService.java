package com.example.demo.infraestructuras.service;

import com.example.demo.api.models.request.Ticketrequest;
import com.example.demo.api.models.response.FlyResponse;
import com.example.demo.api.models.response.TicketResponde;
import com.example.demo.dominan.entity.Ticket;
import com.example.demo.dominan.repository.CustomerRepository;
import com.example.demo.dominan.repository.FlyRepository;
import com.example.demo.dominan.repository.TicketRepository;
import com.example.demo.infraestructuras.abstract_service.IticketService;
import com.example.demo.infraestructuras.helpers.BlackListHelpers;
import com.example.demo.infraestructuras.helpers.CustomerHelper;
import com.example.demo.infraestructuras.helpers.EmailHealpers;
import com.example.demo.util.Best_Travel_Util;
import com.example.demo.util.enunm.Tables;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements IticketService {

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final CustomerHelper customerHelper;
    private BlackListHelpers blackListHelpers;
    private final EmailHealpers mailHealpers;




    @Override
    public TicketResponde create(Ticketrequest request) {
        blackListHelpers.isInBlackListCustomer(request.getIdClient());
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        log.info("fly********************** saved with id: {}", fly.getId());

        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();
        log.info("customer***************** saved with id: {}", customer.getDni());

        var ticketPersist = Ticket.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(charge_price_percentage)))
                .purchaseDate(LocalDate.now())
                .arrivalDate(Best_Travel_Util.getRandomLater())
                .departureDate(Best_Travel_Util.getRandomSoon())
                .build();

        var ticketPersisted = this.ticketRepository.save(ticketPersist);
        this.customerHelper.incrase(customer.getDni(),TicketService.class);
        log.info("ticket************************ saved with id: {}", ticketPersisted.getId());//ME SALE EL LOG SYSTEM OUT
        if(Objects.nonNull(request.getEmail())) {
            this.mailHealpers.sendmail(request.getEmail(),customer.getFullName(), Tables.ticket.name());
        }
        return entityToResponse(ticketPersisted);
    }

    @Override
    public TicketResponde read(UUID id) {
        var ticketFromBD=this.ticketRepository.findById(id).orElseThrow();
        return this.entityToResponse(ticketFromBD);
    }

    @Override
    public TicketResponde update(Ticketrequest request, UUID id) {
        var ticketUpdate=ticketRepository.findById(id).orElseThrow();
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();

        ticketUpdate.setFly(fly);
        ticketUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(charge_price_percentage)));
        ticketUpdate.setDepartureDate(Best_Travel_Util.getRandomSoon());
        ticketUpdate.setArrivalDate((Best_Travel_Util.getRandomLater()));

        var ticketUpdated=this.ticketRepository.save(ticketUpdate);
        log.info("ticket************************ update with id: {}", ticketUpdated.getId());

        return this.entityToResponse(ticketUpdated);
    }

    @Override
    public void delete(UUID id) {
        var ticketTodelete=ticketRepository.findById(id).orElseThrow();
        this.ticketRepository.delete(ticketTodelete);


    }

    private TicketResponde entityToResponse(Ticket ticketEntity) {
        var response = new TicketResponde();
//       response.setId(ticketEntity.getId()); por eso pongo la libreria BeanUtils me hace lo mismo mactchea todo igual
        BeanUtils.copyProperties(ticketEntity, response);
        log.info("TicketResponde****************************************** saved with id: {}", response);

        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(ticketEntity.getFly(), flyResponse);

        response.setFly(flyResponse);
        return response;
    }

    @Override
    public BigDecimal findPrice(Long flyId) {
        var fly=this.flyRepository.findById(flyId).orElseThrow();
        return fly.getPrice().add(fly.getPrice().multiply(charge_price_percentage));
    }
    public static final  BigDecimal charge_price_percentage=BigDecimal.valueOf(0.25);
}
