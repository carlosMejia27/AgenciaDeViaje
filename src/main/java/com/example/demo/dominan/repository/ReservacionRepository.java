package com.example.demo.dominan.repository;

import com.example.demo.dominan.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservacionRepository extends CrudRepository<Reservation, UUID> {
}
