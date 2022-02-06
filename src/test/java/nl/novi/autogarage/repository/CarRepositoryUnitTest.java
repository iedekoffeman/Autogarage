package nl.novi.autogarage.repository;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.model.Car;

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
public class CarRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;



    @Test
    void testFindById() {


        //given
        Car car = new Car();
        car.setLicenseplate("24-xz-zg");
        entityManager.persist(car);
        entityManager.flush();

        //when
        Optional<Car> found = carRepository.findById(3);

        //then
        int expected = 3;
        int actual = found.get().getId();

        assertEquals(expected, actual);
    }

    @Test
    void testFindByLicenseplate() {


        //when
        Car found = carRepository.findAllByLicenseplateContainingIgnoreCase("24-xz-zg");

        //then
        String expected = "24-xz-zg";
        String actual = found.getLicenseplate().toLowerCase();

        assertEquals(expected, actual);

    }
}
