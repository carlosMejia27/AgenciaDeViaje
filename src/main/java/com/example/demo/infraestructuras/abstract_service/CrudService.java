package com.example.demo.infraestructuras.abstract_service;

import jakarta.persistence.Id;

import java.util.UUID;

public interface CrudService <RQ,RS,ID>{

    RS create(RQ request);
    RS read(ID id);

    RS update(RQ request, UUID id);

    void delete(ID id);
}
