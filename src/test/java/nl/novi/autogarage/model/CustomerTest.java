package nl.novi.autogarage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        this.customer = new Customer();
        this.customer.setFirstname("John");
        this.customer.setLastname("Doe");
        this.customer.setPhonenumber("0681036012");
    }

    @Test
    void testGetFirstName() {
        String expectFirstname = "John";
        String actualFirstname = this.customer.getFirstname();
        assertEquals(expectFirstname, actualFirstname);
    }

    @Test
    void testGetLastName() {
        String expectLastname = "Doe";
        String actualLastname = this.customer.getLastname();
        assertEquals(expectLastname, actualLastname);
    }

}
