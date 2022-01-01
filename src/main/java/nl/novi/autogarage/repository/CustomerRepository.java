package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByLastnameContainingIgnoreCase(String lastname);

}
