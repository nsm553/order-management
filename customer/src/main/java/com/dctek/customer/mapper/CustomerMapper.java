package com.dctek.customer.mapper;

import com.dctek.customer.model.Customer;
import com.dctek.customer.rest.dto.CreateCustomerDto;
import com.dctek.customer.rest.dto.CustomerDto;
import com.dctek.customer.rest.dto.UpdateCustomerDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

    Customer toCustomer(CreateCustomerDto createCustomerDto);

    CustomerDto toCustomerDto(Customer customer);

    void updateCustomerFromDto(UpdateCustomerDto updateCustomerDto, @MappingTarget Customer customer);

}
