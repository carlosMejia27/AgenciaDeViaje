package com.example.demo.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelResponde implements Serializable {

    private Long id;
    private String name;
    private String address;
    private Integer rating;
    private BigDecimal price;
//   private ReservationResponse reservation;

}
