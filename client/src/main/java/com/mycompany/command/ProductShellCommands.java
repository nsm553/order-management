package com.mycompany.command;

import com.google.gson.Gson;
import com.mycompany.client.ProductApiClient;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class ProductShellCommands {

    private final ProductApiClient productApiClient;
    private final Gson gson;

    @ShellMethod("Get product by id")
    public String getProduct(String id) { return productApiClient.getProduct(id).map(gson::toJson).block(); }

    @ShellMethod("Get all products")
    public List<String> getProducts() {
        return productApiClient.getProducts().map(gson::toJson).collectList().block();
    }

}
