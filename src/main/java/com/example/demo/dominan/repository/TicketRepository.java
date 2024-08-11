package com.example.demo.dominan.repository;

import com.example.demo.dominan.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
}
