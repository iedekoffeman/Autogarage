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
    public ResponseEntity<Object> addCar(@Valid @RequestBody CarRequestDto carRequestDto) {

        int newId = carService.addCar(carRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }
    @PutMapping(value = "/cars/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable int id, @RequestBody Car car) {

        carService.updateCar(id, car);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/cars/{id}")
    public ResponseEntity<Object> partialupdateCar(@PathVariable int id, @RequestBody Car car) {

        carService.partialUpdateCar(id, car);
        return ResponseEntity.noContent().build();
    }

}
