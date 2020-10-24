package com.dctek.orderapi.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import com.dctek.orderapi.model.Order;
import com.dctek.orderapi.model.OrderKey;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface OrderRepository extends ReactiveCassandraRepository<Order, OrderKey> {

    Mono<Order> findByKeyOrderId(UUID id);

}
