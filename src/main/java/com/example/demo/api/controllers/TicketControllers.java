package com.example.demo.api.controllers;

import com.example.demo.api.models.request.Ticketrequest;
import com.example.demo.api.models.response.Errorsresponse;
import com.example.demo.api.models.response.TicketResponde;
import com.example.demo.infraestructuras.abstract_service.IticketService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name ="Ticket")
public class TicketControllers {

    private final IticketService ticketService;


    @PostMapping("/ticket")
    public ResponseEntity<TicketResponde> post(@RequestBody Ticketrequest request){
        return ResponseEntity.ok(ticketService.create(request));
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketResponde> get(@PathVariable UUID id){
        return ResponseEntity.ok(ticketService.read(id));
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<TicketResponde> update(@PathVariable UUID id ,@RequestBody Ticketrequest request){
        return ResponseEntity.ok(ticketService.update(request,id));
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ticket")
    public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam Long flyid){
        return ResponseEntity.ok(Collections.singletonMap("flyPrice",this.ticketService.findPrice(flyid)));
    }
}
