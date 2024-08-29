package com.example.demo.dominan.repository.mongo;

import com.example.demo.dominan.entities.documents.AppUserDocuments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends MongoRepository<AppUserDocuments, String> {

    Optional<AppUserDocuments> findByUsername(String username);
}
