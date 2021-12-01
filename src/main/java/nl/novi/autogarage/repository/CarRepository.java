package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

    Iterable<Car> findAllByLicenseplateContainingIgnoreCase(String licenseplate);
    Iterable<Car> findAllByLicenseplate(String Licenseplate);
}
