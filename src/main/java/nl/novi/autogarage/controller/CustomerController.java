package nl.novi.autogarage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    //attributes
    private List<String> Customer = new ArrayList<>();

    //constructor
    public CustomerController() {
        Customer.add("Iede");
    }

    @GetMapping(value =  "/customer")
    public List<String> getCustomer() {
        return Customer;
    }
}
