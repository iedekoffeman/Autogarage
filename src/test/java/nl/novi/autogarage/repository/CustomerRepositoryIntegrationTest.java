package nl.novi.autogarage.repository;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.model.Customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@DataJpaTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
public class CustomerRepositoryIntegrationTest {

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
        Optional<Customer> found = customerRepository.findById(4);

        //then
        String expected = "John" ;
        String actual = found.get().getFirstname() ;

        assertEquals(expected, actual);
    }

}
