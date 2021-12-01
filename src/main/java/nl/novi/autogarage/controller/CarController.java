package nl.novi.autogarage.controller;

import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public ResponseEntity<Object> getCars(@RequestParam(name = "licenseplate", defaultValue="") String licenseplate) {
        return ResponseEntity.ok(carService.getCars(licenseplate));
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable int id) {

        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/cars")
    public ResponseEntity<Object> addCar(@RequestBody Car car) {

        int newId = carService.addCar(car);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }

}
