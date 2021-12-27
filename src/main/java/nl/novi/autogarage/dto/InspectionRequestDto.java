package nl.novi.autogarage.dto;

import java.util.Date;

public class InspectionRequestDto {

    private Date appointmentDate;

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
