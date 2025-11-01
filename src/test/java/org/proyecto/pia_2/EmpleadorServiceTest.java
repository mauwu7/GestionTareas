package org.proyecto.pia_2;
import org.proyecto.pia_2.controller.EmpleadorController;
import org.proyecto.pia_2.service.EmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = EmpleadorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmpleadorServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmpleadorService empleadorService;
}
