package nl.novi.autogarage.dto;

import nl.novi.autogarage.model.AppointmentStatus;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RepairRequestDto {

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
