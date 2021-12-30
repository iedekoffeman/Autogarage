package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.RepairRequestDto;
import nl.novi.autogarage.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RestController
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping(value = "/repairs")
    public ResponseEntity<Object> getRepairs(@RequestParam(value = "date", required = false) LocalDate date) {
        return ResponseEntity.ok(repairService.getRepairs(date));


    }
    @GetMapping(value = "/repairs/{id}")
    public ResponseEntity<Object> getRepair(@PathVariable int id) {
        return ResponseEntity.ok(repairService.getRepair(id));
    }

    @DeleteMapping(value = "/repairs/{id}")
    public ResponseEntity<Object> deleteInspection(@PathVariable int id) {

        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/repairs")
    public ResponseEntity<Object> addInspection(@Valid @RequestBody RepairRequestDto repairRequestDto) {

        int newId = repairService.addRepair(repairRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value = "/repairs/{id}")
    public ResponseEntity<Object> updateRepair(@PathVariable int id, @Valid @RequestBody RepairRequestDto repairRequestDto) {

        repairService.updateInspection(id, repairRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/repairs/{id}")
    public ResponseEntity<Object> partialupdateRepair(@PathVariable int id, @Valid @RequestBody RepairRequestDto repairRequestDto) {

        repairService.partialUpdateRepair(id, repairRequestDto);
        return ResponseEntity.noContent().build();
    }


}
