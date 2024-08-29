package com.example.demo.dominan.repository.jpa;

import com.example.demo.dominan.entities.jpa.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
}
