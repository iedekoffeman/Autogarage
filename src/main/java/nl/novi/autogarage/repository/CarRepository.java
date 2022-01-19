package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findAllByLicenseplateContainingIgnoreCase(String licenseplate);

}
