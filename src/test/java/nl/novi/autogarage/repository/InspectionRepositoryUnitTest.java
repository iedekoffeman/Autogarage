package nl.novi.autogarage.repository;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.model.AppointmentStatus;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.model.Customer;

import nl.novi.autogarage.model.Inspection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
public class InspectionRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InspectionRepository inspectionRepository;


    @Test
    void testFindById() {


        //given
        Inspection inspection = new Inspection();
        inspection.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);
        inspection.setAppointmentDate(LocalDate.of(2022, 05, 20));
        entityManager.persist(inspection);
        entityManager.flush();

        //when
        Optional<Inspection> found = inspectionRepository.findById(3);

        //then
        int expected = 3;
        int actual = found.get().getId();

        assertEquals(expected, actual);
    }

}

