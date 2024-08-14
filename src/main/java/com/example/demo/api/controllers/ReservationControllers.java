package com.example.demo.api.controllers;

import com.example.demo.api.models.request.ReservationRequest;
import com.example.demo.api.models.request.Ticketrequest;
import com.example.demo.api.models.response.Errorsresponse;
import com.example.demo.api.models.response.ReservationResponse;
import com.example.demo.api.models.response.TicketResponde;
import com.example.demo.infraestructuras.abstract_service.IreservationsService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name ="Reservations")
public class ReservationControllers {

    private final IreservationsService reservationsService;

    @ApiResponse(
            responseCode = "400",
            description = "cuando la respuesta es invalida respondemos con esto",
            content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation= Errorsresponse.class))
            }
    )
    @PostMapping("/reservation")
    public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationsService.create(request));
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(reservationsService.read(id));
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<ReservationResponse> update(@Valid @PathVariable UUID id ,@RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationsService.update(request,id));
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.reservationsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reservation")
    public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam Long id){
        return ResponseEntity.ok(Collections.singletonMap("ReservacionPrice",this.reservationsService.findPrice(id)));
    }

}
