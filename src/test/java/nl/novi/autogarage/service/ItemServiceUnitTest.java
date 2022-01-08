package nl.novi.autogarage.service;

import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.ItemRequestDto;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.Item;
import nl.novi.autogarage.repository.ItemRepository;
import nl.novi.autogarage.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
//@WithMockUser(username = "admin", roles = {"ADMIN"})
public class ItemServiceUnitTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Mock
    Item item;

    @MockBean
    ItemRequestDto itemRequestDto;


    @BeforeEach
    public void setUp() {
    }


    @Test
    public void testGetItemById()  {

        Item item = new Item();
        item.setName("Koppakking vervangen");
        item.setPrice(new BigDecimal(175.22));


        when(itemRepository.findById(4))
                .thenReturn(java.util.Optional.ofNullable(item));

        int id = 4;
        String expected =  "Koppakking vervangen";

        Item found = itemService.getItem(id);

        assertEquals(expected, found.getName());
    }

    @Test
    void testGetItemByIdNotFoundThrowsRecordNotFoundException()  {
        int id = 10;

        // Setup our mock repository
        // Mockito
        //       .doReturn(null).when(ItemRepository).findById(id);

        when(itemRepository.findById(id)).thenReturn(Optional.ofNullable(null)).thenThrow(new RecordNotFoundException("An Item with ID 10 does not exist"));

        // Execute the service call
        RecordNotFoundException exception = Assertions.assertThrows(
                RecordNotFoundException.class,
                () -> itemService.getItem(id));

        String expectedMessage = "An Item with ID 10 does not exist.";
        String actualMessage = exception.getMessage();

        // Assert the response
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetAllDeficiencies() {

        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        Item item2 = new Item();
        item1.setName("Koppakking vervangen");
        item1.setPrice(new BigDecimal(175.29));
        item2.setName("Olie bijgevuld");
        item2.setPrice(new BigDecimal(11.99));
        items.add(item1);
        items.add(item2);

        when(itemRepository.findAll())
                .thenReturn(items);

        Iterable<Item> found = itemService.getAllItems();

        assertIterableEquals(items, found);

    }

    @Test
    void testDeleteItemByID() {

        int itemId = 1;

        itemService.deleteItem(itemId);

        verify(itemRepository, times(1)).deleteById(eq(itemId));


    }


}
