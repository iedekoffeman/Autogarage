package nl.novi.autogarage.dto;

import java.time.LocalDate;

public class InspectionRequestDto {

    private LocalDate appointmentDate;

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
