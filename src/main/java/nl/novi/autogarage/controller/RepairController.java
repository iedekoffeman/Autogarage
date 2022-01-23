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
@RequestMapping(value = "/api/v1/repairs")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getRepair(@PathVariable int id) {
        return ResponseEntity.ok(repairService.getRepair(id));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllRepairs(@RequestParam(value = "date", required = false) LocalDate date) {
        return ResponseEntity.ok(repairService.getAllRepairs(date));


    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteRepair(@PathVariable int id) {

        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createRepair(@Valid @RequestBody RepairRequestDto repairRequestDto) {

        int newId = repairService.createRepair(repairRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateRepair(@PathVariable int id, @Valid @RequestBody RepairRequestDto repairRequestDto) {

        repairService.updateRepair(id, repairRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<Object> partialupdateRepair(@PathVariable int id, @Valid @RequestBody RepairRequestDto repairRequestDto) {

        repairService.partialUpdateRepair(id, repairRequestDto);
        return ResponseEntity.noContent().build();
    }


}
