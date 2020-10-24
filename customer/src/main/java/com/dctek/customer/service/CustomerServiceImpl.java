package com.dctek.customer.service;

import com.dctek.customer.exception.CustomerNotFoundException;
import com.dctek.customer.model.Customer;
import com.dctek.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<Customer> validateAndGetCustomer(Long id) {
        return customerRepository.findById(id).switchIfEmpty(Mono.error(new CustomerNotFoundException(id)));
    }

    @Override
    public Flux<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Void> deleteCustomer(Customer customer) {
        return customerRepository.delete(customer);
    }

}
