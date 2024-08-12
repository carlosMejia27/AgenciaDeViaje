package com.example.demo.infraestructuras.service;

import com.example.demo.api.models.response.HotelResponde;
import com.example.demo.dominan.entity.Hotel;
import com.example.demo.dominan.repository.HotelRepository;
import com.example.demo.infraestructuras.abstract_service.IhotelService;
import com.example.demo.util.SortType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class HotelService implements IhotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Page<HotelResponde> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest=null;

        switch (sortType){
            case NONE -> pageRequest=pageRequest.of(page,size);
            case LOWER -> pageRequest=pageRequest.of(page,size, Sort.by(Field_by_sort).ascending());
            case UPPER -> pageRequest=pageRequest.of(page,size, Sort.by(Field_by_sort).descending());
        }
        return hotelRepository.findAll(pageRequest).map(this::entityToResponse);

    }

    @Override
    public Set<HotelResponde> readLessPrice(BigDecimal price) {
        return hotelRepository.findByPriceLessThan(price)
                .stream()
                .map(hotelPrecio -> this.entityToResponse(hotelPrecio))
                .collect(Collectors.toSet());

    }

    @Override
    public Set<HotelResponde> readBetweenPrices(BigDecimal min, BigDecimal max) {
        return hotelRepository.findByPriceIsBetween(min,max)
                .stream()
                .map(hotelPrecio -> this.entityToResponse(hotelPrecio))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<HotelResponde> ReadByReating(Integer raiting) {
        return hotelRepository.findByRatingGreaterThan(raiting)
                .stream()
                .map(hotelPrecio -> this.entityToResponse(hotelPrecio))
                .collect(Collectors.toSet());
    }

    private HotelResponde entityToResponse(Hotel entity){
        HotelResponde response=new HotelResponde();
        BeanUtils.copyProperties(entity,response);
        return response;

    }
}
