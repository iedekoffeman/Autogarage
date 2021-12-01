package nl.novi.autogarage.service;

import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getCars(String licenseplate) {

        if(licenseplate.isEmpty()) {
            return carRepository.findAll();
        } else {
            return carRepository.findAllByLicenseplateContainingIgnoreCase(licenseplate);
        }

    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    public int addCar(Car car) {
        Car newCar = carRepository.save(car);
        return car.getId();

    }

}
