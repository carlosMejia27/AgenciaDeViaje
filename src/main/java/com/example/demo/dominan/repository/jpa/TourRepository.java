package com.example.demo.dominan.repository.jpa;

import com.example.demo.dominan.entities.jpa.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour,Long> {
}
