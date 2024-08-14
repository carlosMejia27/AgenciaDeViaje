package com.example.demo.api.models.response;

import com.example.demo.util.enunm.Aerolinia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyResponse {

    private Long id;
    private Double originLat ;
    private Double originLng ;
    private Double destinyLng ;
    private Double destinyLat ;
    private String originName;
    private String destinyName;
    private BigDecimal price;
    private Aerolinia aeroLine;


}
