package com.example.demo.api.controllers;

import com.example.demo.api.models.response.HotelResponde;
import com.example.demo.infraestructuras.abstract_service.IhotelService;
import com.example.demo.util.enunm.SortType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name ="Hotel")
public class HotelControllers {

    private final IhotelService hotelService;

    @GetMapping("/hotel")
    public ResponseEntity<Page<HotelResponde>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType){
        if (Objects.isNull(sortType)) sortType=sortType.NONE;
        var response=this.hotelService.readAll(page, size, sortType);
        return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @GetMapping("/hotel_less_price")
    public ResponseEntity<Set<HotelResponde>> getLessPrice(
            @RequestParam BigDecimal price){
        var response=this.hotelService.readLessPrice(price);
        return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @GetMapping("/hotel_Between_price")
    public ResponseEntity<Set<HotelResponde>> getBetweenPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max){
        var response=this.hotelService.readBetweenPrices(min,max);
        return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @GetMapping("/hotel_reating")
    public ResponseEntity<Set<HotelResponde>> getReating(
            @RequestParam Integer reiting){
        if (reiting>4) reiting=4;
        if (reiting<1) reiting=1;
        var response=this.hotelService.ReadByReating(reiting);
        return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }
}
