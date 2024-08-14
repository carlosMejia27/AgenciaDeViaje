package com.example.demo.api.controllers;

import com.example.demo.api.models.request.TourResquest;
import com.example.demo.api.models.response.Errorsresponse;
import com.example.demo.api.models.response.TourResponse;
import com.example.demo.infraestructuras.abstract_service.ITourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(name ="Tour")
public class TourController {

    private final ITourService tourService;

    @ApiResponse(
            responseCode = "400",
            description = "cuando la respuesta es invalida respondemos con esto",
            content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation= Errorsresponse.class))
            }
    )
    @Operation(summary = "guarda un sistema de tour basado en una lista de hoteles y vuelos")
    @PostMapping("/tour")
    public ResponseEntity<TourResponse> post(@RequestBody TourResquest request){
        return ResponseEntity.ok(this.tourService.create(request));
    }

    @Operation(summary = "retorna un tour con el id pasado")
    @GetMapping("/tour/{id}")
    public ResponseEntity<TourResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.tourService.read(id));
    }

    @Operation(summary = "retorna un tour con el id borrado")
    @DeleteMapping("/tour/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.tourService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "borra un ticket ")
    @PatchMapping("/{tourId}/remove_ticket/{ticketiId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long tourId,@PathVariable UUID ticketiId){
        this.tourService.deleteTicket(tourId,ticketiId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "agrega un ticket un ticket ")
    @PatchMapping("/{tourId}/add_ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long flyId ){
        var response= Collections.singletonMap("ticketId",this.tourService.addTicket(flyId,tourId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "borra un reservacion ")
    @PatchMapping("/{tourId}/remove_reservation/{ReservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long tourId,@PathVariable UUID ReservationId){
        this.tourService.removeReservation(tourId,ReservationId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "agrega un reservacion ")
    @PatchMapping("/{tourId}/add_reservation/{hotelId}")
    public ResponseEntity<Map<String, UUID>> postTicket(
            @PathVariable Long tourId,
            @PathVariable Long hotelId,
            @RequestParam Integer totalDays
    ){
        var response= Collections.singletonMap("ticketId",this.tourService.addReservation(tourId,hotelId,totalDays));
        return ResponseEntity.ok(response);
    }



}
