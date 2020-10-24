package com.dctek.productapi.mapper;

import com.dctek.productapi.model.Product;
import com.dctek.productapi.rest.dto.CreateProductDto;
import com.dctek.productapi.rest.dto.ProductDto;
import com.dctek.productapi.rest.dto.UpdateProductDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    Product toProduct(CreateProductDto createProductDto);

    ProductDto toProductDto(Product product);

    void updateProductFromDto(UpdateProductDto updateProductDto, @MappingTarget Product product);

}
