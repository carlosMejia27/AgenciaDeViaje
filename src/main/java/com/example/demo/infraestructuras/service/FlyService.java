package com.example.demo.infraestructuras.service;

import com.example.demo.api.models.response.FlyResponse;
import com.example.demo.dominan.entity.Fly;
import com.example.demo.dominan.repository.FlyRepository;
import com.example.demo.infraestructuras.abstract_service.IFlyService;
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
public class FlyService implements IFlyService {

    private final FlyRepository flyRepository;
    @Override
    public Page<FlyResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest=null;

        switch (sortType){
            case NONE -> pageRequest=pageRequest.of(page,size);
            case LOWER -> pageRequest=pageRequest.of(page,size, Sort.by(Field_by_sort).ascending());
            case UPPER -> pageRequest=pageRequest.of(page,size, Sort.by(Field_by_sort).descending());
        }


        return flyRepository.findAll(pageRequest).map(this::entityToResponse);
        //.map(fly -> this.entityToResponse(fly));
    }

    @Override
    public Set<FlyResponse> readLessPrice(BigDecimal price) {
        return flyRepository.selectLessPrice(price)
                .stream()
                .map(fly -> this.entityToResponse(fly))
                .collect(Collectors.toSet());

    }

    @Override
    public Set<FlyResponse> readBetweenPrices(BigDecimal min, BigDecimal max) {
        return flyRepository.selectbetweenprice(min,max)
                .stream()
                .map(fly -> this.entityToResponse(fly))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FlyResponse> readByOriginDestiny(String origen, String destiny) {

        return flyRepository.selectOriginDestino(origen,destiny)
                .stream()
                .map(fly -> this.entityToResponse(fly))
                .collect(Collectors.toSet());
    }

    private FlyResponse entityToResponse(Fly entity){
        FlyResponse response=new FlyResponse();
        BeanUtils.copyProperties(entity,response);
        return response;

    }
}
