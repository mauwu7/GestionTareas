package org.proyecto.pia_2;
import org.junit.jupiter.api.Test;
import org.proyecto.pia_2.controller.EmpleadorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value= EmpleadorController.class)
public class EmpleadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void PostEmpleadoTest(){

    }


}
