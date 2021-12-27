package nl.novi.autogarage.controller;

import nl.novi.autogarage.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @GetMapping(value = "/inspection")
    public ResponseEntity<Object> getInspections(@RequestParam(name = "date", defaultValue="") LocalDate date) {
        return ResponseEntity.ok(inspectionService.getInspections(date));
    }


}
