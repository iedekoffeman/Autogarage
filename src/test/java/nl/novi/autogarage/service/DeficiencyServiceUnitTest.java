package nl.novi.autogarage.service;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.DeficiencyRequestDto;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.Deficiency;
import nl.novi.autogarage.repository.DeficiencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
public class DeficiencyServiceUnitTest {

    @Autowired
    private DeficiencyService deficiencyService;

    @MockBean
    private DeficiencyRepository deficiencyRepository;

    @Mock
    Deficiency deficiency;

    @MockBean
    DeficiencyRequestDto deficiencyRequestDto;


    @BeforeEach
    public void setUp() {
    }


    @Test
    public void testGetDeficiencyById()  {

        deficiency = new Deficiency();
        deficiency.setDescription("Koppakking lek");


        when(deficiencyRepository.findById(4))
                .thenReturn(java.util.Optional.ofNullable(deficiency));

        int id = 4;
        String expected =  "Koppakking lek";

        Deficiency found = deficiencyService.getDeficiency(id);

        assertEquals(expected, found.getDescription());
    }

    @Test
    void testGetDeficiencyByIdNotFoundThrowsRecordNotFoundException()  {
        int id = 10;

        // Setup our mock repository
       // Mockito
         //       .doReturn(null).when(deficiencyRepository).findById(id);

        when(deficiencyRepository.findById(id)).thenReturn(Optional.ofNullable(null)).thenThrow(new RecordNotFoundException("A Deficiency with ID 10 does not exist"));

        // Execute the service call
        RecordNotFoundException exception = Assertions.assertThrows(
                RecordNotFoundException.class,
                () -> deficiencyService.getDeficiency(id));

        String expectedMessage = "A Deficiency with ID 10 does not exist.";
        String actualMessage = exception.getMessage();

        // Assert the response
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetAllDeficiencies() {

        List<Deficiency> deficiencies = new ArrayList<>();
        Deficiency deficiency1 = new Deficiency();
        Deficiency deficiency2 = new Deficiency();
        deficiency1.setDescription("Koppakking lek");
        deficiency2.setDescription("Oliepeil laag");
        deficiencies.add(deficiency1);
        deficiencies.add(deficiency2);

        when(deficiencyRepository.findAll())
                .thenReturn(deficiencies);

        Iterable<Deficiency> found = deficiencyService.getAllDeficiencies();

        assertIterableEquals(deficiencies, found);

    }

    @Test
    void testDeleteDeficiencyByID() {

        int deficienyId = 1;

        deficiencyService.deleteDeficiency(deficienyId);

        verify(deficiencyRepository, times(1)).deleteById(eq(deficienyId));


    }


}