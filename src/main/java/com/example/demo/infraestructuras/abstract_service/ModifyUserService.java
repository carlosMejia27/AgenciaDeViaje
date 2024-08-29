package com.example.demo.infraestructuras.abstract_service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ModifyUserService {

    Map<String,Boolean> enable(String username);
    Map<String, Set<String>> addRole(String username, String role);
    Map<String, Set<String> > removeAddRole(String username, String role);


}
