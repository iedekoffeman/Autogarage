package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.CarRequestDto;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getCar(@PathVariable int id) {
        return ResponseEntity.ok(carService.getCar(id));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllCars(@RequestParam(required = false) String licenseplate) {

        if (licenseplate == null || licenseplate.isEmpty()) {

            return ResponseEntity.ok(carService.getAllCars());

        } else {

            return ResponseEntity.ok(carService.getCarsByLicensePlate(licenseplate));

        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable int id) {

        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarRequestDto carRequestDto) {

        int newId = carService.createCar(carRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable int id, @RequestBody Car car) {

        carService.updateCar(id, car);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> partialupdateCar(@PathVariable int id, @RequestBody Car car) {

        carService.partialUpdateCar(id, car);
        return ResponseEntity.noContent().build();
    }

}
