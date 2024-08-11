package com.example.demo.api.controllers;

import com.example.demo.api.models.request.Ticketrequest;
import com.example.demo.api.models.response.TicketResponde;
import com.example.demo.infraestructuras.abstract_service.IticketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
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
}
