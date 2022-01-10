package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.CarRequestDto;
import nl.novi.autogarage.exception.BadRequestException;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.exception.FileStorageException;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Value("${app.upload.dir:${user.home}}")
    private String uploadDirectory;  // relative to root
    private final Path uploads = Paths.get("uploads");


    @Autowired
    private CarRepository carRepository;

    public void init() {
        try {
            Files.createDirectory(uploads);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public Car getCar(int id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            return optionalCar.get();
        } else {
            throw new RecordNotFoundException("A Car with ID " + id + " does not exist.");
        }
    }

    public Iterable<Car> getAllCars() {

        return carRepository.findAll();

    }

    public Car getCarsByLicensePlate(String licenseplate) {

        return carRepository.findAllByLicenseplateContainingIgnoreCase(licenseplate);

    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    public int createCar(CarRequestDto carRequestDto) {

        String licenseplate = carRequestDto.getLicenseplate();
        List<Car> cars = (List<Car>) carRepository.findAllByLicenseplateContainingIgnoreCase(licenseplate);

        if (cars.size() != 0) {
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

    public int uploadFile(int id, MultipartFile file) {

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path copyLocation = this.uploads.resolve(file.getOriginalFilename());

        try {
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + originalFilename + ". Please try again!");
        }

        Car existingCar = carRepository.findById(id).orElse(null);

        if (!(file == null) && !file.isEmpty()) {
            if (existingCar != null) {
                existingCar.setLicenseRegistrationFileName(originalFilename);
            } else {
                throw new RecordNotFoundException("A car with this ID has not found");
            }
        }

        Car savedCar = carRepository.save(existingCar);

        return savedCar.getId();

    }

}