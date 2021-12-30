package nl.novi.autogarage.dto;


import com.fasterxml.jackson.annotation.JsonValue;
import nl.novi.autogarage.model.AppointmentStatus;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.validation.ValueOfEnum;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class InspectionRequestDto {

    @Future(message = "{error.appointmentDate.notInPast}")
    @NotNull(message = "{error.appointmentDate.notnull}" )
    private LocalDate appointmentDate;

    @NotNull(message = "{error.appointmentStatus.notnull}")
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
