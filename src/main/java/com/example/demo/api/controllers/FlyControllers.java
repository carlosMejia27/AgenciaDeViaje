package com.example.demo.api.controllers;

import com.example.demo.api.models.response.FlyResponse;
import com.example.demo.infraestructuras.abstract_service.IFlyService;
import com.example.demo.util.SortType;
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
public class FlyControllers {

    private final IFlyService flyService;

    @GetMapping("/fly")
    public ResponseEntity<Page<FlyResponse>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = true)SortType sortType){
        if (Objects.isNull(sortType)) sortType=sortType.NONE;
        var response=this.flyService.readAll(page, size, sortType);
        return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @GetMapping("/fly_less_price")
    public ResponseEntity<Set<FlyResponse>> getLessPrice(
            @RequestParam BigDecimal price){
        var response=this.flyService.readLessPrice(price);
        return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @GetMapping("/fly_Between_price")
    public ResponseEntity<Set<FlyResponse>> getBetweenPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max){
        var response=this.flyService.readBetweenPrices(min,max);
        return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @GetMapping("/fly_origin_destiny")
    public ResponseEntity<Set<FlyResponse>> getOriginDestiny(
            @RequestParam String origin,
            @RequestParam String destiny){
        var response=this.flyService.readByOriginDestiny(origin,destiny);
        return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }
}
