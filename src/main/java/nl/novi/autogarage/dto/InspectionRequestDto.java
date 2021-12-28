package nl.novi.autogarage.dto;


import nl.novi.autogarage.model.AppointmentStatus;

import javax.validation.constraints.Future;
import java.time.LocalDate;

public class InspectionRequestDto {

    @Future(message = "The date of inspection must be in the future.")
    private LocalDate appointmentDate;

    private AppointmentStatus appointmentStatus;

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
