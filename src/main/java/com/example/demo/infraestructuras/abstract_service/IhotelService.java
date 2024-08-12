package com.example.demo.infraestructuras.abstract_service;

import com.example.demo.api.models.response.HotelResponde;

import java.util.Set;

public interface IhotelService extends CatalogoService<HotelResponde> {
    Set<HotelResponde> ReadByReating(Integer raiting);
}
