package com.dctek.orderapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${product-api.url}")
    private String productApiUrl;

    @Value("${customer-api.url}")
    private String customerApiUrl;

    @Bean(name = "productApiWebClient")
    WebClient productApiWebClient() {
        return WebClient.create(productApiUrl);
    }

    @Bean(name = "customerApiWebClient")
    WebClient customerApiWebClient() {
        return WebClient.create(customerApiUrl);
    }

}
