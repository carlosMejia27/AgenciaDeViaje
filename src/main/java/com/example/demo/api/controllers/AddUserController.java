package com.example.demo.api.controllers;

import com.example.demo.infraestructuras.abstract_service.ModifyUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name ="User")
public class AddUserController {

    private final ModifyUserService modifyUserService;

    @Operation(summary ="enable Or Disable user" )
    @PatchMapping(path = "enable-or-disable")
    public ResponseEntity<Map<String,Boolean>> enableOrDisable(@RequestParam String username){
        return ResponseEntity.ok(this.modifyUserService.enable(username));

    }

    @Operation(summary ="Add role user" )
    @PatchMapping(path = "add-role")
    public ResponseEntity<Map<String, Set<String>>> addRole(@RequestParam String username, @RequestParam  String role){
        return ResponseEntity.ok(this.modifyUserService.addRole(username,role));

    }

    @Operation(summary ="remove role user" )
    @PatchMapping(path = "remove-role")
    public ResponseEntity<Map<String, Set<String>>> removeRole(@RequestParam String username, @RequestParam  String role){
        return ResponseEntity.ok(this.modifyUserService.removeAddRole(username,role));

    }
}
