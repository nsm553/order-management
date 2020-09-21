package com.mycompany.command;

import com.google.gson.Gson;
import com.mycompany.client.CustomerApiClient;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.Email;
import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class CustomerShellCommands {

    private final CustomerApiClient customerApiClient;
    private final Gson gson;

    @ShellMethod("Get customer by id")
    public String getCustomer(String id) {
        return customerApiClient.getCustomer(id).map(gson::toJson).block();
    }

    @ShellMethod("Get all customers")
    public List<String> getCustomers() {
        return customerApiClient.getCustomers().map(gson::toJson).collectList().block();
    }

}
