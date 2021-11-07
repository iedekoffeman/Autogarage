package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

   @PostMapping(value = "/customers")
   public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return ResponseEntity.created(null).build();

   }

   @PutMapping(value = "/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customers.set(id, customer);
        return ResponseEntity.noContent().build();
   }

   @PatchMapping(value = "/customers/{id}")
    public ResponseEntity<Object> partialupdateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer existingCustomer = customers.get(id);
        if (!customer.getFirstname().isEmpty()) {
            existingCustomer.setFirstname(customer.getFirstname());
        }
       if (!customer.getLastname().isEmpty()) {
           existingCustomer.setLastname(customer.getLastname());
       }

       customers.set(id, existingCustomer);
       return ResponseEntity.noContent().build();
   }

}
