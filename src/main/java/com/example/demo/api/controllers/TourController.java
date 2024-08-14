package com.example.demo.api.controllers;

import com.example.demo.api.models.request.TourResquest;
import com.example.demo.api.models.response.TourResponse;
import com.example.demo.infraestructuras.abstract_service.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TourController {

    private final ITourService tourService;

    @PostMapping("/tour")
    public ResponseEntity<TourResponse> post(@RequestBody TourResquest request){
        return ResponseEntity.ok(this.tourService.create(request));
    }

    @GetMapping("/tour/{id}")
    public ResponseEntity<TourResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.tourService.read(id));
    }

    @DeleteMapping("/tour/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.tourService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{tourId}/remove_ticket/{ticketiId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long tourId,@PathVariable UUID ticketiId){
        this.tourService.deleteTicket(tourId,ticketiId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{tourId}/add_ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> postTicket(@PathVariable Long tourId, @PathVariable Long flyId ){
        var response= Collections.singletonMap("ticketId",this.tourService.addTicket(flyId,tourId));
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{tourId}/remove_reservation/{ReservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long tourId,@PathVariable UUID ReservationId){
        this.tourService.removeReservation(tourId,ReservationId);
        return ResponseEntity.noContent().build();
    }

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
