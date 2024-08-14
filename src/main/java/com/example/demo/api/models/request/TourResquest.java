package com.example.demo.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourResquest implements Serializable {

    public  String custumerId;

    private Set<TourFlyRequest> flights;

    private Set<TourHotelRequest> Hotels;
}
