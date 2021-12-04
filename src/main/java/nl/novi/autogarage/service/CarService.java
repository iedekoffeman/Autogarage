package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.CarRequestDto;
import nl.novi.autogarage.exception.BadRequestException;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int addCar(CarRequestDto carRequestDto) {

        String licenseplate = carRequestDto.getLicenseplate();
        List<Car> cars = (List<Car>) carRepository.findAllByLicenseplate(licenseplate);
        if(cars.size()!=0) {
            throw new BadRequestException("License plate number already exists!!");
        }

        Car car = new Car();
        car.setLicenseplate(carRequestDto.getLicenseplate());

        Car newCar = carRepository.save(car);
        return newCar.getId();

    }
    public void updateCar(int id, Car car) {
        Car existingCar = carRepository.findById(id).orElse(null);

        if (!car.getLicenseplate().isEmpty()) {
            existingCar.setLicenseplate(car.getLicenseplate());
        }

        carRepository.save(existingCar);
    }
    public void partialUpdateCar(int id, Car car) {
        Car existingCar = carRepository.findById(id).orElse(null);

        if (!(car.getLicenseplate() == null) && !car.getLicenseplate().isEmpty()) {
            existingCar.setLicenseplate(car.getLicenseplate());
        }

        carRepository.save(existingCar);
    }

}
