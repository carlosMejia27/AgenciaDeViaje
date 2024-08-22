package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebClientConfig {

    @Value(value = "${api.base.url}") ///https://api.apilayer.com
    private String baseUrl;
    @Value(value = "${api.api.key}") ///nPEOfdSDofUaJfmwkpsl198eviHn14n1
    private String apiKey;

    @Value(value = "${api.api-key.header}") ///apikey
    private String apiKeyHeader;


    @Bean(name = "currency")
//    @Primary
    public WebClient CurrentWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(apiKeyHeader, apiKey)
                .build();
    }

}
