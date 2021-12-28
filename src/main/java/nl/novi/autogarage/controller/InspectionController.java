package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.CustomerRequestDto;
import nl.novi.autogarage.dto.InspectionRequestDto;
import nl.novi.autogarage.exception.BadRequestException;
import nl.novi.autogarage.model.Customer;
import nl.novi.autogarage.model.Inspection;
import nl.novi.autogarage.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RestController
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @GetMapping(value = "/inspections")
    public ResponseEntity<Object> getInspections(@RequestParam(value = "date", required = false) LocalDate date) {
            return ResponseEntity.ok(inspectionService.getInspections(date));


    }
    @GetMapping(value = "/inspections/{id}")
    public ResponseEntity<Object> getInspection(@PathVariable int id) {
        return ResponseEntity.ok(inspectionService.getInspection(id));
    }

    @DeleteMapping(value = "/inspections/{id}")
    public ResponseEntity<Object> deleteInspection(@PathVariable int id) {

        inspectionService.deleteInspection(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/inspections")
    public ResponseEntity<Object> addInspection(@Valid @RequestBody InspectionRequestDto inspectionRequestDto) {

        int newId = inspectionService.addInspection(inspectionRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value = "/inspections/{id}")
    public ResponseEntity<Object> updateInspection(@PathVariable int id, @RequestBody Inspection inspection) {

        inspectionService.updateInspection(id, inspection);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/inspections/{id}")
    public ResponseEntity<Object> partialupdateCustomer(@PathVariable int id, @RequestBody Inspection inspection) {

        inspectionService.partialUpdateInspection(id, inspection);
        return ResponseEntity.noContent().build();
    }


}
