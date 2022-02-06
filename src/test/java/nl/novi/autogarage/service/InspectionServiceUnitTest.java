package nl.novi.autogarage.service;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.InspectionRequestDto;
import nl.novi.autogarage.model.AppointmentStatus;
import nl.novi.autogarage.model.Inspection;
import nl.novi.autogarage.repository.InspectionRepository;
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
public class InspectionServiceUnitTest {

    @Autowired
    private InspectionService inspectionService;

    @MockBean
    private InspectionRepository inspectionRepository;

    @Mock
    Inspection inspection;

    @MockBean
    InspectionRequestDto inspectionRequestDto;

    @Captor
    ArgumentCaptor<Inspection> inspectionArgumentCaptor;

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void testGetInspectionById()  {

        inspection = new Inspection();
        inspection.setAppointmentDate(LocalDate.of(2022, 05, 20));
        inspection.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);


        when(inspectionRepository.findById(4))
                .thenReturn(java.util.Optional.ofNullable(inspection));

        int id = 4;
        LocalDate expected = LocalDate.of(2022, 05, 20);

        Inspection found = inspectionService.getInspection(id);

        assertEquals(expected, found.getAppointmentDate());
    }

    @Test
    void testGetAllInspectionsByDate() {

        LocalDate date = LocalDate.of(2022, 05, 20);

        List<Inspection> inspections = new ArrayList<>();
        Inspection inspection1 = new Inspection();
        inspection1.setAppointmentDate(LocalDate.of(2022, 05, 20));
        inspection1.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);
        inspections.add(inspection1);

        when(inspectionRepository.findAllByAppointmentDate(date))
                .thenReturn(inspections);

        Iterable<Inspection> expected = inspections;

        Iterable<Inspection> found = inspectionService.getAllInspections(date);

        assertIterableEquals(expected, found);

    }

    @Test
    void testDeleteInspectionByID() {

        int inspectionId = 1;

        inspectionService.deleteInspection(inspectionId);

        verify(inspectionRepository, times(1)).deleteById(eq(inspectionId));


    }

    @Test
    void testCreateInspection() {

        inspectionRequestDto = new InspectionRequestDto();
        inspectionRequestDto.setAppointmentDate(LocalDate.of(2022, 05, 20));
        inspectionRequestDto.setAppointmentStatus(AppointmentStatus.APPOINTMENT_SCHEDULED);

        inspection = new Inspection();
        inspection.setAppointmentDate(inspectionRequestDto.getAppointmentDate());
        inspection.setAppointmentStatus(inspectionRequestDto.getAppointmentStatus());

        when(inspectionRepository.save(any(Inspection.class)))
                .thenReturn(inspection);

        int newInspection = inspectionService.createInspection(inspectionRequestDto);

        assertThat(newInspection).isEqualTo(inspection.getId());

    }

}
