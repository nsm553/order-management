package com.dctek.customer.rest;

import com.dctek.customer.mapper.CustomerMapper;
import com.dctek.customer.model.Customer;
import com.dctek.customer.rest.dto.CreateCustomerDto;
import com.dctek.customer.rest.dto.CustomerDto;
import com.dctek.customer.rest.dto.UpdateCustomerDto;
import com.dctek.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<CustomerDto> getCustomers() {
        return customerService.getCustomers().map(customerMapper::toCustomerDto);
    }

    @GetMapping("/{id}")
    public Mono<CustomerDto> getCustomer(@PathVariable Long id) {
        return customerService.validateAndGetCustomer(id).map(customerMapper::toCustomerDto);
    }

    @PostMapping
    public Mono<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto) {
        Customer customer = customerMapper.toCustomer(createCustomerDto);
        return customerService.saveCustomer(customer).map(customerMapper::toCustomerDto);
    }

    @PutMapping("/{id}")
    public Mono<CustomerDto> updateCustomer(@PathVariable Long id, @Valid @RequestBody UpdateCustomerDto updateCustomerDto) {
        return customerService.validateAndGetCustomer(id)
                .doOnSuccess(customer -> {
                    customerMapper.updateCustomerFromDto(updateCustomerDto, customer);
                    customerService.saveCustomer(customer).subscribe();
                })
                .map(customerMapper::toCustomerDto);
    }

    @DeleteMapping("/{id}")
    public Mono<CustomerDto> deleteCustomer(@PathVariable Long id) {
        return customerService.validateAndGetCustomer(id)
                .doOnSuccess(customer -> customerService.deleteCustomer(customer).subscribe())
                .map(customerMapper::toCustomerDto);
    }

}
