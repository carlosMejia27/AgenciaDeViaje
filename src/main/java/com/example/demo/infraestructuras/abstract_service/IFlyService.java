package com.example.demo.infraestructuras.abstract_service;

import com.example.demo.api.models.response.FlyResponse;

import java.util.Set;

public interface IFlyService extends CatalogoService<FlyResponse> {
    Set<FlyResponse> readByOriginDestiny(String origen, String destiny);
}
