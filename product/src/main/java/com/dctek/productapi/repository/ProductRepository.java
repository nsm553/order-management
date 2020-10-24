package com.dctek.productapi.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.dctek.productapi.model.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
