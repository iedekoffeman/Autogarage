package nl.novi.autogarage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.CustomerRequestDto;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.security.JwtUtil;
import nl.novi.autogarage.service.CustomerService;
import org.apache.catalina.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        customer.setPhonenumber("0681036012");
        List<Customer> allCustomers = Arrays.asList(customer);

        given(customerService.getAllCustomers()).willReturn(allCustomers);

        mvc.perform(get("/api/v1/customers")
                        .with(user("administratief_medewerker").roles("ADMINISTRATIEFMEDEWERKER"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastname", is("Doe")));

    }
    @Test
    public void testCreateCustomer() throws Exception {

        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setFirstname("Iede");
        customerRequestDto.setLastname("Koffeman");
        customerRequestDto.setPhonenumber("0681036012");

        Customer customer = new Customer();
        customer.setFirstname(customerRequestDto.getFirstname());
        customer.setLastname(customerRequestDto.getLastname());
        customer.setPhonenumber(customerRequestDto.getPhonenumber());

        mvc.perform(post("/api/v1/customers")
                        .with(user("administratief_medewerker").roles("ADMINISTRATIEFMEDEWERKER"))
                .content(asJsonString(customerRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}