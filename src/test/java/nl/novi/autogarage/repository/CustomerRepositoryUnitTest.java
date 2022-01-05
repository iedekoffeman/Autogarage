package nl.novi.autogarage.repository;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.model.Customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@DataJpaTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
public class CustomerRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testFindById() {


        //given
        Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        entityManager.persist(customer);
        entityManager.flush();

        //when
        Optional<Customer> found = customerRepository.findById(3);

        //then
        int expected = 3 ;
        int actual = found.get().getId() ;

        assertEquals(expected, actual);
    }

    @Test
    void testFindByLastName() {


        //given
        Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        entityManager.persist(customer);
        entityManager.flush();

        //when
        Customer found = customerRepository.findByLastnameContainingIgnoreCase("Doe");

        //then
        String expected = "Doe" ;
        String actual = found.getLastname();

        assertEquals(expected, actual);
    }



}
