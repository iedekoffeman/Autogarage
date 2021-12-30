package nl.novi.autogarage.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AppointmentStatus {

    APPOINTMENT_SCHEDULED,
    CUSTOMER_APPROVED,
    REPAIR_COMPLETED,
    DO_NOT_CARRY_OUT;

    @JsonCreator
    public static AppointmentStatus create(String value) {
        if(value == null) {
            return null;
        }
        for(AppointmentStatus v : values()) {
            if(value.equals(v.name())) {
                return v;
            }
        }
        return null;
    }

}
