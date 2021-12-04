package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.CustomerRequestDto;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;

@RestController
public class CustomerController {


    //constructor mag leeg blijven

    @Autowired
    private CustomerService customerService;

    @GetMapping(value =  "/customers")
    //ResponseEntity a class which builds a http request.
    public ResponseEntity<Object> getCustomers(@RequestParam(name="firstname", defaultValue="") String firstname) {
        return ResponseEntity.ok(customerService.getCustomers(firstname)); //Jackson (helper) translates object to json
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id) {

        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

   @PostMapping(value = "/customers")
   public ResponseEntity<Object> addCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {

       int newId = customerService.addCustomer(customerRequestDto);
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

       return ResponseEntity.created(location).build();

   }

   @PutMapping(value = "/customers/{id}")
   public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {

       customerService.updateCustomer(id, customer);
       return ResponseEntity.noContent().build();
   }

   @PatchMapping(value = "/customers/{id}")
   public ResponseEntity<Object> partialupdateCustomer(@PathVariable int id, @RequestBody Customer customer) {

       customerService.partialUpdateCustomer(id, customer);
       return ResponseEntity.noContent().build();
   }

}
