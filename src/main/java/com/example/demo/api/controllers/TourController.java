package com.example.demo.api.controllers;

import com.example.demo.api.models.request.TourResquest;
import com.example.demo.api.models.response.TourResponse;
import com.example.demo.infraestructuras.abstract_service.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TourResponse> delete(@PathVariable Long id){
        this.tourService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
