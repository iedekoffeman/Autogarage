package nl.novi.autogarage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class RepairTest extends Appointment {

    private Repair repair;

    @BeforeEach
    void setUp() {
        this.repair = new Repair();
        this.repair.setAppointmentDate(LocalDate.of(2022,05,20));
        this.repair.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);
    }

    @Test
    void testGetRepairDate() {
        LocalDate expectDate = LocalDate.of(2022,05,20);
        LocalDate actualDate = this.repair.getAppointmentDate();
        assertEquals(expectDate, actualDate);
    }

}