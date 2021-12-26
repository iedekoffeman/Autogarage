package nl.novi.autogarage.service;

import nl.novi.autogarage.model.Appointment;
import nl.novi.autogarage.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public abstract class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Iterable<Appointment> getAppointments(LocalDate appointmentDate) {

        if(appointmentDate == null) {
            return appointmentRepository.findAll();
        } else {
            return appointmentRepository.findAllByAppointmentDate(appointmentDate);
        }

    }

}
