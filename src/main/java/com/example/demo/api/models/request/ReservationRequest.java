package com.example.demo.api.models.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest {

    @Size(min = 18 ,max=20,message="entre 18 y 20 caracteres")
    @NotBlank(message = "Id cliente es obligatorio")
    private String idClient;

    @Positive
    @NotNull(message = "id hotel es obligatorio")
    private Long idHotel;
    @Min(value = 1,message = "minimo un dia")
    @Max(value = 30,message = "maximo 30 dias")
    @NotNull(message = " es obligatorio")
    private Integer totalDays;
    @Email(message = "email valido")
    private String email;
}
