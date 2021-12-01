package nl.novi.autogarage.controller;

import nl.novi.autogarage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public ResponseEntity<Object> getCars(@RequestParam(name = "licenseplate", defaultValue="") String licenseplate) {
        return ResponseEntity.ok(carService.getCars(licenseplate));
    }
}
