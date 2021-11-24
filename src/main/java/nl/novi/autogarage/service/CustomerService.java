package nl.novi.autogarage.service;

import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll(); //Jackson (helper) translates object to json
    }
    public Customer getCustomer(int id) {
        return customerRepository.findById(id).orElse(null);
    }
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
    public int addCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return customer.getId();

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
}
