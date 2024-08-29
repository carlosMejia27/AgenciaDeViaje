package com.example.demo.dominan.repository.jpa;

import com.example.demo.dominan.entities.jpa.Fly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;


@Repository
public interface FlyRepository extends JpaRepository<Fly,Long> {
    @Query("select f from Fly f where f.price < :price")
    Set<Fly> selectLessPrice(BigDecimal price);


    @Query("select f from Fly f where f.price between :min and :max ")
    Set<Fly> selectbetweenprice(BigDecimal min,BigDecimal max);

    @Query("select f from Fly f where f.originName =:origin and f.destinyName=:destiny")
    Set<Fly> selectOriginDestino(String origin,String destiny);
}
