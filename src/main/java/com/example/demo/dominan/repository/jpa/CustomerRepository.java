package com.example.demo.dominan.repository.jpa;

import com.example.demo.dominan.entities.jpa.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,String> {
}
