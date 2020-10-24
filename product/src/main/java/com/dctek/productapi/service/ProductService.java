package com.dctek.productapi.service;

import com.dctek.productapi.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> validateAndGetProduct(String id);

    Flux<Product> getProducts();

    Mono<Product> saveProduct(Product product);

    Mono<Void> deleteProduct(Product product);

}
