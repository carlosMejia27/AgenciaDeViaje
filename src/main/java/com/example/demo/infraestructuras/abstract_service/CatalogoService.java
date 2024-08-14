package com.example.demo.infraestructuras.abstract_service;

import com.example.demo.util.enunm.SortType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Set;

public interface CatalogoService <R>{
    Page<R> readAll(Integer page, Integer size, SortType sortType);
    Set<R> readLessPrice(BigDecimal price);
    Set<R> readBetweenPrices(BigDecimal min ,BigDecimal max);

    String Field_by_sort="price";

}
