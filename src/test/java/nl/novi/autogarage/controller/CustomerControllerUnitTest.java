package nl.novi.autogarage.controller;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.security.JwtUtil;
import nl.novi.autogarage.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
public class CustomerControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testEndpointGetAllCustomers() throws Exception {

        Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        List<Customer> allCustomers = Arrays.asList(customer);

        given(customerService.getAllCustomers()).willReturn(allCustomers);

        mvc.perform(get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastname", is("Doe")));

    }

}