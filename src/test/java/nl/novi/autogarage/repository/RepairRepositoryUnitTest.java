package nl.novi.autogarage.repository;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.model.*;

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
public class RepairRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepairRepository repairRepository;


    @Test
    void testFindById() {


        //given
        Repair repair = new Repair();
        repair.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);
        repair.setAppointmentDate(LocalDate.of(2022, 05, 20));
        entityManager.persist(repair);
        entityManager.flush();

        //when
        Optional<Repair> found = repairRepository.findById(3);

        //then
        int expected = 3;
        int actual = found.get().getId();

        assertEquals(expected, actual);
    }

}