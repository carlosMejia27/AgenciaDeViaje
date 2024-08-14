package com.example.demo.infraestructuras.abstract_service;

import com.example.demo.api.models.request.Ticketrequest;
import com.example.demo.api.models.response.TicketResponde;

import java.math.BigDecimal;
import java.util.UUID;

public interface IticketService extends  CrudService<Ticketrequest, TicketResponde,UUID>{
    BigDecimal findPrice(Long flyId);

}
