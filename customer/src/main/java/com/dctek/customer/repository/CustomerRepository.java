package com.dctek.customer.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.dctek.customer.model.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}
