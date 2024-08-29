package com.example.demo.infraestructuras.service;

import com.example.demo.dominan.repository.mongo.AppUserRepository;
import com.example.demo.infraestructuras.abstract_service.ModifyUserService;
import com.example.demo.util.enunm.Tables;
import com.example.demo.util.exceptions.UserNameNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AddUserService implements ModifyUserService {

    private final AppUserRepository appUserRepository;
    private final String collection_name = "app_users";

    @Override
    public Map<String, Boolean> enable(String username) {
        var user = this.appUserRepository.findByUsername(username).orElseThrow(() -> new UserNameNotFoundException(collection_name));
        user.setEnabled(!user.isEnabled());

        var userSaved = this.appUserRepository.save(user);

        return Collections.singletonMap(userSaved.getUsername(), userSaved.isEnabled());
    }

    @Override
    public Map<String, Set<String>> addRole(String username, String role) {
        var user = this.appUserRepository.findByUsername(username).orElseThrow(() -> new UserNameNotFoundException(collection_name));
        user.getRole().getGrantedAuthorities().add(role);
        var userSaved = this.appUserRepository.save(user);
        var autorities=userSaved.getRole().getGrantedAuthorities();

        log.info("User {} add role {}",userSaved.getUsername(),userSaved.getRole().getGrantedAuthorities().toString());

        return Collections.singletonMap(userSaved.getUsername(), autorities);


    }

    @Override
    public Map<String, Set<String>> removeAddRole(String username, String role) {
        var user = this.appUserRepository.findByUsername(username).orElseThrow(() -> new UserNameNotFoundException(collection_name));
        user.getRole().getGrantedAuthorities().remove(role);
        var userSaved = this.appUserRepository.save(user);
        var autorities=userSaved.getRole().getGrantedAuthorities();
        log.info("User {} Borrado role {}",userSaved.getUsername(),userSaved.getRole().getGrantedAuthorities().toString());

        return Collections.singletonMap(userSaved.getUsername(), autorities);
    }

    @Transactional(readOnly = true)
    private void loadUserByUsername(String username){
        var user = this.appUserRepository.findByUsername(username).orElseThrow(() -> new UserNameNotFoundException(collection_name));

    }
}
