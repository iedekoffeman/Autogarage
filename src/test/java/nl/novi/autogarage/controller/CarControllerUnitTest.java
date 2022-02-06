package nl.novi.autogarage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.CarRequestDto;
import nl.novi.autogarage.model.Car;
import nl.novi.autogarage.security.JwtUtil;
import nl.novi.autogarage.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CarController.class)
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
public class CarControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CarService carService;


    @Test
    public void testEndpointGetAllCars() throws Exception {

        Car car = new Car();
        car.setLicenseplate("24-xz-zg");
        List<Car> allCars = Arrays.asList(car);

        given(carService.getAllCars()).willReturn(allCars);

        mvc.perform(get("/api/v1/cars")
                        .with(user("administratief_medewerker").roles("ADMINISTRATIEFMEDEWERKER"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].licenseplate", is("24-xz-zg")));

    }
    @Test
    public void testCreateCar() throws Exception {

        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setLicenseplate("24-xz-zg");

        Car car = new Car();
        car.setLicenseplate(carRequestDto.getLicenseplate());

        mvc.perform(post("/api/v1/cars")
                        .with(user("administratief_medewerker").roles("ADMINISTRATIEFMEDEWERKER"))
                        .content(asJsonString(carRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}