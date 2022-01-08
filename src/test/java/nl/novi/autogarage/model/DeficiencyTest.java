package nl.novi.autogarage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DeficiencyTest {

    private Deficiency deficiency;

    @BeforeEach
    void setUp() {
        this.deficiency = new Deficiency();
        this.deficiency.setDescription("Koppakking lek");
    }

    @Test
    void testGetDescription() {
        String expectDescription = "Koppakking lek";
        String actualDescription = this.deficiency.getDescription();
        assertEquals(expectDescription, actualDescription);
    }

}