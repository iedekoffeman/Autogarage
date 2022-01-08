package nl.novi.autogarage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        this.item = new Item();
        this.item.setName("Koppakking vervangen");
        this.item.setPrice(BigDecimal.valueOf(175.25));
    }

    @Test
    void testGetRepairPrice() {
        BigDecimal expectPrice = new BigDecimal(175.25);
        BigDecimal actualPrice = this.item.getPrice();
        assertEquals(expectPrice, actualPrice);
    }

    @Test
    void testGetRepairName() {
        String expectName = "Koppakking vervangen";
        String actualName = this.item.getName();
        assertEquals(expectName, actualName);
    }

}
