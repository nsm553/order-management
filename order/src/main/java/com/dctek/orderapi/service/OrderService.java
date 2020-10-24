package com.dctek.orderapi.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.dctek.orderapi.model.Order;

public interface OrderService {

    Flux<Order> getOrders();

    Mono<Order> validateAndGetOrder(UUID id);

    Mono<Order> saveOrder(Order order);

}
