package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.InspectionRequestDto;
import nl.novi.autogarage.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/inspections")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getInspection(@PathVariable int id) {
        return ResponseEntity.ok(inspectionService.getInspection(id));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getInspections(@RequestParam(value = "date", required = false) LocalDate date) {
            return ResponseEntity.ok(inspectionService.getAllInspections(date));


    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteInspection(@PathVariable int id) {

        inspectionService.deleteInspection(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createInspection(@Valid @RequestBody InspectionRequestDto inspectionRequestDto) {

        int newId = inspectionService.createInspection(inspectionRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateInspection(@PathVariable int id, @Valid @RequestBody InspectionRequestDto inspectionRequestDto) {

        inspectionService.updateInspection(id, inspectionRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<Object> partialupdateCustomer(@PathVariable int id, @Valid @RequestBody InspectionRequestDto inspectionRequestDto) {

        inspectionService.partialUpdateInspection(id, inspectionRequestDto);
        return ResponseEntity.noContent().build();
    }


}
