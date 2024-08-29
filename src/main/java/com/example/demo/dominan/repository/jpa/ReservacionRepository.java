package com.example.demo.dominan.repository.jpa;

import com.example.demo.dominan.entities.jpa.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservacionRepository extends CrudRepository<Reservation, UUID> {
}
