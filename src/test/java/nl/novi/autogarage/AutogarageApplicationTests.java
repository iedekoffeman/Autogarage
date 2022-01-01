package nl.novi.autogarage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes={AutogarageApplication.class})
class AutogarageApplicationTests {

    @Test
    @DisplayName("Testing if context is correctly set")
    void contextLoads(ApplicationContext context) {

        assertNotNull(context);


    }

}
