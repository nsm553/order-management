package com.dctek.command;

import com.dctek.client.OrderApiClient;
import com.dctek.client.ProductApiClient;
import com.dctek.dto.CreateOrderDto;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class OrderShellCommands {

    private final OrderApiClient orderApiClient;
    private final ProductApiClient productApiClient;
    private final Gson gson;
    private final Random random;

    @ShellMethod("Get order by id")
    public String getOrder(UUID id) {
        return orderApiClient.getOrder(id).map(gson::toJson).block();
    }

    @ShellMethod("Get order detailed by id")
    public String getOrderDetailed(UUID id) {
        return orderApiClient.getOrderDetailed(id).map(gson::toJson).block();
    }

    @ShellMethod("Get all orders")
    public List<String> getOrders() {
        return orderApiClient.getOrders().map(gson::toJson).collectList().block();
    }

    private Set<CreateOrderDto.ProductDto> getProducts(int numProducts) {
        return productApiClient.getProducts()
                .toStream()
                .limit(numProducts)
                .map(productDto -> new CreateOrderDto.ProductDto(productDto.getId(), getRandomQuantity()))
                .collect(Collectors.toSet());
    }

    private int getRandomQuantity() {
        return 1 + random.nextInt(5);
    }

}
