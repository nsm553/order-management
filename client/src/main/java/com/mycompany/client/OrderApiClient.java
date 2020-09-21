package com.mycompany.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.dto.CreateOrderDto;
import com.mycompany.dto.OrderDetailedDto;
import com.mycompany.dto.OrderDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;

@Component
public class OrderApiClient {

    @Autowired
    @Qualifier("orderApiWebClient")
    private WebClient webClient;

    public Mono<OrderDto> getOrder(UUID id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .retrieve()
                .bodyToMono(OrderDto.class);
    }

    public Mono<OrderDetailedDto> getOrderDetailed(UUID id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{id}/detailed").build(id))
                .retrieve()
                .bodyToMono(OrderDetailedDto.class);
    }

    public Flux<OrderDto> getOrders() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(OrderDto.class);
    }


}
