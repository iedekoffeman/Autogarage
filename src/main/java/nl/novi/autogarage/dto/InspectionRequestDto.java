package nl.novi.autogarage.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class InspectionRequestDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Please provide a date.")
    private LocalDate appointmentDate;

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
