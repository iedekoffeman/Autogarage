package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    //ResponseEntity a class which builds a http request.
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok(customers); //Jackson (helper) translates object to json
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable int id) {
            return ResponseEntity.ok(customers.get(id));
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id) {
        customers.remove(id);
        return ResponseEntity.noContent().build();
    }


}
