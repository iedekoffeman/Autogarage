package nl.novi.autogarage.service;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.RepairRequestDto;
import nl.novi.autogarage.model.AppointmentStatus;
import nl.novi.autogarage.model.Repair;
import nl.novi.autogarage.repository.RepairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
public class RepairServiceUnitTest {

    @Autowired
    private RepairService repairService;

    @MockBean
    private RepairRepository repairRepository;

    @Mock
    Repair repair;

    @MockBean
    RepairRequestDto repairRequestDto;

    @Captor
    ArgumentCaptor<Repair> repairArgumentCaptor;

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void testGetRepairById()  {

        repair = new Repair();
        repair.setAppointmentDate(LocalDate.of(2022, 05, 20));
        repair.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);


        when(repairRepository.findById(4))
                .thenReturn(java.util.Optional.ofNullable(repair));

        int id = 4;
        LocalDate expected = LocalDate.of(2022, 05, 20);

        Repair found = repairService.getRepair(id);

        assertEquals(expected, found.getAppointmentDate());
    }

    @Test
    void testGetAllRepairsByDate() {

        LocalDate date = LocalDate.of(2022, 05, 20);

        List<Repair> repairs = new ArrayList<>();
        Repair repair1 = new Repair();
        repair1.setAppointmentDate(LocalDate.of(2022, 05, 20));
        repair1.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);
        repairs.add(repair1);

        when(repairRepository.findAllByAppointmentDate(date))
                .thenReturn(repairs);

        Iterable<Repair> expected = repairs;

        Iterable<Repair> found = repairService.getAllRepairs(date);

        assertIterableEquals(expected, found);

    }

    @Test
    void testDeleteRepairByID() {

        int repairId = 1;

        repairService.deleteRepair(repairId);

        verify(repairRepository, times(1)).deleteById(eq(repairId));


    }

    @Test
    void testCreateRepair() {

        repairRequestDto = new RepairRequestDto();
        repairRequestDto.setAppointmentDate(LocalDate.of(2022, 05, 20));
        repairRequestDto.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);

        repair = new Repair();
        repair.setAppointmentDate(repairRequestDto.getAppointmentDate());
        repair.setAppointmentStatus(repairRequestDto.getAppointmentStatus());

        when(repairRepository.save(any(Repair.class)))
                .thenReturn(repair);

        int newRepair = repairService.createRepair(repairRequestDto);

        assertThat(newRepair).isEqualTo(repair.getId());

    }

}
