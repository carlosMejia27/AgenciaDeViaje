package com.example.demo.infraestructuras.helpers;

import com.example.demo.infraestructuras.dtos.CurrencyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Currency;

@Component
@Slf4j
public class ApiCurrenceConnectorHelper {

    private final WebClient webClient;
    @Value(value = "${api.base-currency}") ///USD
    private String baseCurrency;

    private static String BASE_CURRENCY_QUERY_PARAM="?base={base}";
    private static String SYMBOL_CURRENCY_QUERY_PARAM="&symbols={symbol}";
    private static String CURRENCY_PATH="/fixer/latest";

    public ApiCurrenceConnectorHelper(WebClient webClient) {
        this.webClient = webClient;
    }

    public CurrencyDTO getCurrency(Currency currency){
        return this.webClient
                .get()
                .uri(uri-> uri.path(CURRENCY_PATH)
                        .query(BASE_CURRENCY_QUERY_PARAM)
                        .query(SYMBOL_CURRENCY_QUERY_PARAM)
                        .build(baseCurrency,currency.getCurrencyCode()))
                .retrieve()
                .bodyToMono(CurrencyDTO.class)
                .block();
    }
}
