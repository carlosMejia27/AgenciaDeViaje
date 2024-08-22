package com.example.demo.infraestructuras.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;

@Data
@ToString
public class CurrencyDTO implements Serializable {

    @JsonProperty(value ="date")
    private LocalDate exchangedate;

    private Map<Currency, BigDecimal> rates;
}
