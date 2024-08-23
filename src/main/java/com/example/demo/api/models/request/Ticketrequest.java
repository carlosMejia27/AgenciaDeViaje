package com.example.demo.api.models.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ticketrequest {
    private String idClient;
    private Long idFly;
    @Email(message = "email valido")
    private String email;
}
