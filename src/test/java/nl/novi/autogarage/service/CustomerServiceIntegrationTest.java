package nl.novi.autogarage.service;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
//@WithMockUser(username = "admin", roles = {"ADMIN"})
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testFindById()  {

        customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");


        Mockito
                .when(customerRepository.findById(4))
                .thenReturn(java.util.Optional.ofNullable(customer));

        int id = 4;
        String expected =  "John";

        Customer found = customerService.getCustomer(id);

        assertEquals(expected, found.getFirstname());
    }


}
