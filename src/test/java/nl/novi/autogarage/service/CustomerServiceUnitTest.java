package nl.novi.autogarage.service;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.CustomerRequestDto;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
//@WithMockUser(username = "admin", roles = {"ADMIN"})
public class CustomerServiceUnitTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @Mock
    CustomerRequestDto customerRequestDto;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetCustomerById()  {

        customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");


        when(customerRepository.findById(4))
                .thenReturn(java.util.Optional.ofNullable(customer));

        int id = 4;
        String expected =  "Doe";

        Customer found = customerService.getCustomer(id);

        assertEquals(expected, found.getLastname());
    }

    @Test
    public void testGetCustomerByLastName() {

        customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");

        when(customerRepository.findByLastnameContainingIgnoreCase(customer.getLastname()))
                .thenReturn(customer);

        String name = "Doe";
        String expected =  "Doe";

        Customer found = customerService.getCustomerByLastName(name);

        assertEquals(expected, found.getLastname());

    }

    @Test
    void testGetCustomerByLastNameNotFound() {
        String name = "DoeXXX";

        // Setup our mock repository
        Mockito
                .doReturn(null).when(customerRepository)
                .findByLastnameContainingIgnoreCase(name);

        // Execute the service call
        Customer found = customerService.getCustomerByLastName(name);

        // Assert the response
        assertNull(found, "Widget should not be found");
    }

    @Test
    void testGetAllCustomers() {

        List<Customer> customer = new ArrayList<>();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customer1.setFirstname("John");
        customer1.setLastname("Doe");
        customer2.setFirstname("Jane");
        customer2.setLastname("Roe");
        customer.add(customer1);
        customer.add(customer2);

        when(customerRepository.findAll())
                .thenReturn(customer);

        Iterable<Customer> found = customerService.getAllCustomers();

        assertIterableEquals(customer, found);

    }
    @Test
    public void testCreateCustomer() {

        customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");

        customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setFirstname("John");
        customerRequestDto.setLastname("Doe");

        when(customerRepository.save(customer))
                .thenReturn(customer);


        int newCustomer = customerService.createCustomer(customerRequestDto);


        assertThat(newCustomer).isEqualTo(customer.getId());
    }



}
