package org.proyecto.pia_2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.proyecto.pia_2.service.impl.EmpleadorServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class WebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmpleadorServiceimpl empleadorService;

    @Autowired
    EmpleadorRepository empleadorRepository;

    @Test
    public void PostEmpleadorTest()throws Exception{
        Empleador empleadorTest = new Empleador("Mario bezares","mario@gmail.com","21727377");
        empleadorService.agregarEmpleador(empleadorTest);
        mockMvc.perform(MockMvcRequestBuilders.post("/empleadores"));

        assertTrue(empleadorRepository.findByUsername(empleadorTest.getUsername()).equals("Mario bezares"));
    }

}
