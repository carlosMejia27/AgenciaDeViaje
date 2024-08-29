package com.example.demo.dominan.repository.jpa;

import com.example.demo.dominan.entities.jpa.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    Set<Hotel> findByPriceLessThan(BigDecimal price);
    Set<Hotel> findByPriceIsBetween(BigDecimal min,BigDecimal max);
    Set<Hotel> findByRatingGreaterThan(Integer rating);

//    Optional<Hotel> findByReservationID(Integer id);

}
