package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.DeficiencyRequestDto;
import nl.novi.autogarage.model.Deficiency;
import nl.novi.autogarage.service.DeficiencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class DeficiencyController {

    @Autowired
    private DeficiencyService deficiencyService;

    @GetMapping(value =  "/deficiencies")
    //ResponseEntity a class which builds a http request.
    public ResponseEntity<Object> getDeficiencies() {
        return ResponseEntity.ok(deficiencyService.getDeficiencies());
    }

    @GetMapping(value = "/deficiencies/{id}")
    public ResponseEntity<Object> getDeficiency(@PathVariable int id) {
        return ResponseEntity.ok(deficiencyService.getDeficiency(id));
    }

    @DeleteMapping(value = "/deficiencies/{id}")
    public ResponseEntity<Object> deleteDeficiency(@PathVariable int id) {

        deficiencyService.deleteDeficiency(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/deficiencies")
    public ResponseEntity<Object> addDeficiency(@Valid @RequestBody DeficiencyRequestDto deficiencyRequestDto) {

        int newId = deficiencyService.addDeficiency(deficiencyRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value = "/deficiencies/{id}")
    public ResponseEntity<Object> updateDeficiency(@PathVariable int id, @RequestBody Deficiency deficiency) {

        deficiencyService.updateDeficiency(id, deficiency);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/deficiencies/{id}")
    public ResponseEntity<Object> partialupdateDeficiency(@PathVariable int id, @RequestBody Deficiency deficiency) {

        deficiencyService.partialUpdateDeficiency(id, deficiency);
        return ResponseEntity.noContent().build();
    }

}
