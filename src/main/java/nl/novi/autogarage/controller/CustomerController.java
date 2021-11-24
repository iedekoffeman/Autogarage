package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;

@RestController
public class CustomerController {


    //constructor mag leeg blijven

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value =  "/customers")
    //ResponseEntity a class which builds a http request.
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok(customerRepository.findAll()); //Jackson (helper) translates object to json
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable int id) {
        return ResponseEntity.ok(customerRepository.findById(id));
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

   @PostMapping(value = "/customers")
   public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
       Customer newCustomer = customerRepository.save(customer);
       int newId = customer.getId();
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

       return ResponseEntity.created(location).build();

   }

   @PutMapping(value = "/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {

       Customer existingCustomer = customerRepository.findById(id).orElse(null);

       if (!customer.getFirstname().isEmpty()) {
           existingCustomer.setFirstname(customer.getFirstname());
       }
       if (!customer.getLastname().isEmpty()) {
           existingCustomer.setLastname(customer.getLastname());
       }

       customerRepository.save(existingCustomer);

       return ResponseEntity.noContent().build();
   }

   @PatchMapping(value = "/customers/{id}")
    public ResponseEntity<Object> partialupdateCustomer(@PathVariable int id, @RequestBody Customer customer) {

        Customer existingCustomer = customerRepository.findById(id).orElse(null);

        if (!(customer.getFirstname() == null) && !customer.getFirstname().isEmpty()) {
            existingCustomer.setFirstname(customer.getFirstname());
        }
        if (!(customer.getLastname() == null) && !customer.getLastname().isEmpty()) {
           existingCustomer.setLastname(customer.getLastname());
        }

        customerRepository.save(existingCustomer);

        return ResponseEntity.noContent().build();
   }

}
