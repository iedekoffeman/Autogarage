package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    //attributes
    private List<Customer> customers = new ArrayList<>();

    //constructor
    public CustomerController() {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customer1.setFirstname("Iede");
        customer1.setLastname("Koffeman");
        customer2.setFirstname("Jan");
        customer2.setLastname("Jansen");
        customers.add(customer1);
        customers.add(customer2);
    }

    @GetMapping(value =  "/customers")
    //ResponseEntiity a class whicht builds a http request.
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok(customers);
    }
}
