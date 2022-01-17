package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.CustomerRequestDto;
import nl.novi.autogarage.model.AppointmentStatus;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {


    //constructor mag leeg blijven

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping(value =  "")
    //ResponseEntity a class which builds a http request.
    public ResponseEntity<Object> getCustomers(@RequestParam(required = false) String lastname) {

        if (lastname == null || lastname.isEmpty()) {

            return ResponseEntity.ok(customerService.getAllCustomers());


        } else {

            return ResponseEntity.ok(customerService.getCustomerByLastName(lastname)); //Jackson (helper) translates object to json


        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {

        int newId = customerService.createCustomer(customerRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id) {

        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

   @PutMapping(value = "/{id}")
   public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {

       customerService.updateCustomer(id, customer);
       return ResponseEntity.noContent().build();
   }

   @PatchMapping(value = "/{id}")
   public ResponseEntity<Object> partialupdateCustomer(@PathVariable int id, @RequestBody Customer customer) {

       customerService.partialUpdateCustomer(id, customer);
       return ResponseEntity.noContent().build();
   }

    @GetMapping(value = "/{id}/cars")
    public ResponseEntity<Object> getCustomerCars(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getCustomerCars(id));
    }

    @PostMapping(value = "/{id}/cars")
    public ResponseEntity<Object> addCustomerCar(@PathVariable int id, @RequestBody Car car) {
        customerService.addCustomerCar(id, car);
        return ResponseEntity.created(null).build();
    }

    @GetMapping(value = "/{id}/appointments")
    public ResponseEntity<Object> getCustomerAppointments(@PathVariable int id, @RequestParam AppointmentStatus status ) {
        return ResponseEntity.ok(customerService.getCustomerAppointments(id, status));
    }


}
