package com.example.demo.infraestructuras.service;

import com.example.demo.api.models.response.FlyResponse;
import com.example.demo.dominan.entity.Fly;
import com.example.demo.dominan.repository.FlyRepository;
import com.example.demo.infraestructuras.abstract_service.IFlyService;
import com.example.demo.infraestructuras.helpers.CacheConstantes;
import com.example.demo.util.enunm.SortType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@Slf4j
@AllArgsConstructor
public class FlyService implements IFlyService {

    private final FlyRepository flyRepository;
//    private final WebClient webClient;

//    public FlyService(FlyRepository flyRepository,@Qualifier(value = "base") WebClient webClient) {
//        this.flyRepository = flyRepository;
//        this.webClient = webClient;
//    }

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
    @Cacheable(value = CacheConstantes.FLY_CACHE_NAME)
    public Set<FlyResponse> readLessPrice(BigDecimal price) {

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return flyRepository.selectLessPrice(price)
                .stream()
                .map(fly -> this.entityToResponse(fly))
                .collect(Collectors.toSet());

    }



    @Override
    @Cacheable(value = CacheConstantes.FLY_CACHE_NAME)
    public Set<FlyResponse> readBetweenPrices(BigDecimal min, BigDecimal max) {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return flyRepository.selectbetweenprice(min,max)
                .stream()
                .map(fly -> this.entityToResponse(fly))
                .collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = CacheConstantes.FLY_CACHE_NAME)
    public Set<FlyResponse> readByOriginDestiny(String origen, String destiny) {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
