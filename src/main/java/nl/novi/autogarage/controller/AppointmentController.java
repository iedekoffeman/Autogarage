package nl.novi.autogarage.controller;

import nl.novi.autogarage.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public abstract class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/appointment")
    public ResponseEntity<Object> getAppointments(@RequestParam(name = "date", defaultValue="") LocalDate date) {
        return ResponseEntity.ok(appointmentService.getAppointments(date));
    }

}
