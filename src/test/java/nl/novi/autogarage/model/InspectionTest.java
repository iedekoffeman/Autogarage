package nl.novi.autogarage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class InspectionTest extends Appointment {

    private Inspection inspection;

    @BeforeEach
    void setUp() {
        this.inspection = new Inspection();
        this.inspection.setAppointmentDate(LocalDate.of(2022,05,20));
        this.inspection.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);
    }

    @Test
    void testGetInspectionDate() {
        LocalDate expectDate = LocalDate.of(2022,05,20);
        LocalDate actualDate = this.inspection.getAppointmentDate();
        assertEquals(expectDate, actualDate);
    }

}