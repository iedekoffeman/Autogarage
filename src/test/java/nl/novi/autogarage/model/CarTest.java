package nl.novi.autogarage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setLicenseplate("24-xz-zg");
    }

    @Test
    void testGetLicenseplate() {
        String expectLicenseplate = "24-xz-zg";
        String actualLicenseplate = this.car.getLicenseplate();
        assertEquals(expectLicenseplate, actualLicenseplate);
    }


}
