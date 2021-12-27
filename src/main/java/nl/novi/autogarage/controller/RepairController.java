package nl.novi.autogarage.controller;

import nl.novi.autogarage.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RepairController {

    @Autowired
    private RepairService repairService;

    @GetMapping(value = "/repair")
    public ResponseEntity<Object> getInspections(@RequestParam(name = "date", defaultValue="") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return ResponseEntity.ok(repairService.getRepairs(date));
    }


}
