package com.mycompany.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.mycompany.dto.CreateCustomerDto;
import com.mycompany.dto.CustomerDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerApiClient {

    @Autowired
    @Qualifier("customerApiWebClient")
    private WebClient webClient;

    public Mono<CustomerDto> getCustomer(String id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .retrieve()
                .bodyToMono(CustomerDto.class);
    }

    public Flux<CustomerDto> getCustomers() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(CustomerDto.class);
    }

}
