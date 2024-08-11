package com.example.demo.dominan.repository;

import com.example.demo.dominan.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour,Long> {
}
