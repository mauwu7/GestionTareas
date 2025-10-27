package org.proyecto.pia_2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proyecto.pia_2.controller.EmpleadorController;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value= EmpleadorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmpleadorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    EmpleadorRepository empleadorRepository;
    private String json;


    @BeforeEach
    public void setUp() {
        json = """
                {
                       "username":"nuevoEmpleador",
                       "email":"empleador@gmail.com",
                       "password":"algunacontraseña"
                }
                """;

        when(empleadorRepository.save(any())).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    public void PostEmpleadoTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/addEmpleador")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
    }


}
