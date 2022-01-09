package nl.novi.autogarage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import nl.novi.autogarage.AutogarageApplication;
import nl.novi.autogarage.dto.DeficiencyRequestDto;
import nl.novi.autogarage.model.Deficiency;
import nl.novi.autogarage.security.JwtUtil;
import nl.novi.autogarage.service.DeficiencyService;
import org.apache.catalina.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DeficiencyController.class)
@ContextConfiguration(classes={AutogarageApplication.class})
@EnableConfigurationProperties
public class DeficiencyControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private DeficiencyService deficiencyService;


    @Test
    public void testEndpointGetAllDeficiencys() throws Exception {

        Deficiency deficiency = new Deficiency();
        deficiency.setDescription("Koppakking lek");
        List<Deficiency> allDeficiencys = Arrays.asList(deficiency);

        given(deficiencyService.getAllDeficiencies()).willReturn(allDeficiencys);

        mvc.perform(get("/api/v1/deficiencies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", is("Koppakking lek")));

    }
    @Test
    public void testEndpointCreateDeficiency() throws Exception {

        DeficiencyRequestDto deficiencyRequestDto = new DeficiencyRequestDto();
        deficiencyRequestDto.setDescription("Koppakking lek");

        Deficiency deficiency = new Deficiency();
        deficiency.setDescription(deficiencyRequestDto.getDescription());

        mvc.perform(post("/api/v1/deficiencies")
                        .content(asJsonString(deficiencyRequestDto))
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
