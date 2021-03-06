package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.CustomerRequestDto;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.*;
import nl.novi.autogarage.repository.CarRepository;
import nl.novi.autogarage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    public Customer getCustomer(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new RecordNotFoundException("A Customer with ID " + id + " does not exist.");
        }
    }

    public Iterable<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }


    public Customer getCustomerByLastName(String lastname) {

        return customerRepository.findByLastnameContainingIgnoreCase(lastname);

    }

    public int createCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = new Customer();
        customer.setFirstname(customerRequestDto.getFirstname());
        customer.setLastname(customerRequestDto.getLastname());
        customer.setPhonenumber(customerRequestDto.getPhonenumber());

        Customer newCustomer = customerRepository.save(customer);
        return newCustomer.getId();

    }


    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomer(int id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);

        if (!customer.getFirstname().isEmpty()) {
            existingCustomer.setFirstname(customer.getFirstname());
        }
        if (!customer.getLastname().isEmpty()) {
            existingCustomer.setLastname(customer.getLastname());
        }

        customerRepository.save(existingCustomer);
    }

    public void partialUpdateCustomer(int id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);

        if (!(customer.getFirstname() == null) && !customer.getFirstname().isEmpty()) {
            existingCustomer.setFirstname(customer.getFirstname());
        }
        if (!(customer.getLastname() == null) && !customer.getLastname().isEmpty()) {
            existingCustomer.setLastname(customer.getLastname());
        }

        customerRepository.save(existingCustomer);
    }

    public Iterable<Car> getCustomerCars(int id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer.getCars();

        } else {

            throw new RecordNotFoundException("A Customer with ID " + id + " does not exist.");
        }


    }

    public void addCustomerCar(int id, Car car) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            List<Car> cars = customer.getCars();
            car.setOwner(customer);
            carRepository.save(car);
            cars.add(car);
            customerRepository.save(customer);
        } else {

            throw new RecordNotFoundException("A Customer with ID " + id + " does not exist.");
        }


    }

}
