package nl.novi.autogarage.service;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.CarRequestDto;
import nl.novi.autogarage.dto.CustomerRequestDto;
import nl.novi.autogarage.exception.BadRequestException;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.repository.CarRepository;
import nl.novi.autogarage.repository.CustomerRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.Record;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
//@WithMockUser(username = "admin", roles = {"ADMIN"})
public class CarServiceUnitTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Mock
    Car car;

    @MockBean
    CarRequestDto carRequestDto;

    @Captor
    ArgumentCaptor<String> carArgumentCaptor;

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void testGetCarById()  {

        car = new Car();
        car.setLicenseplate("24-xz-zg");


        when(carRepository.findById(4))
                .thenReturn(java.util.Optional.ofNullable(car));

        int id = 4;
        String expected =  "24-xz-zg";

        Car found = carService.getCar(id);

        assertEquals(expected, found.getLicenseplate().toLowerCase());
    }

    @Test
    public void testGetCarByLicenseplate() {

        car = new Car();
        car.setLicenseplate("24-xz-zg");

        when(carRepository.findAllByLicenseplateContainingIgnoreCase(car.getLicenseplate()))
                .thenReturn(car);

        String licenseplate = "24-xz-zg";
        String expected =  "24-xz-zg";

        Car found = carService.getCarsByLicensePlate(licenseplate);

        assertEquals(expected, found.getLicenseplate());

    }

    @Test
    void testGetCustomerByLicenseplateNotFound() {
        String licenseplate = "24-xz-ww";

        // Setup our mock repository
        Mockito
                .doReturn(null).when(carRepository)
                .findAllByLicenseplateContainingIgnoreCase(licenseplate);

        // Execute the service call
        Car found = carService.getCarsByLicensePlate(licenseplate);

        // Assert the response
        assertNull(found, "Widget should not be found");
    }

    @Test
    void testGetAllCars() {

        List<Car> car = new ArrayList<>();
        Car car1 = new Car();
        Car car2 = new Car();
        car1.setLicenseplate("24-xz-zg");
        car2.setLicenseplate("27-yw-zd");
        car.add(car1);
        car.add(car2);

        when(carRepository.findAll())
                .thenReturn(car);

        Iterable<Car> found = carService.getAllCars();

        assertIterableEquals(car, found);

    }

    @Test
    void testDeleteCarByID() {

        int carId = 1;

        carService.deleteCar(carId);

        verify(carRepository, times(1)).deleteById(eq(carId));


    }

    @Test
    void testCreateCar() {

        carRequestDto = new CarRequestDto();
        carRequestDto.setLicenseplate("24-xz-zg");

        String licenseplate = carRequestDto.getLicenseplate();

        List<Car> cars = new ArrayList<>();

        car = new Car();
        car.setLicenseplate(carRequestDto.getLicenseplate());

        when(carRepository.save(any(Car.class)))
                .thenReturn(car);

        int newCar = carService.createCar(carRequestDto);

        assertThat(newCar).isEqualTo(car.getId());

    }

}