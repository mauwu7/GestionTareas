package org.proyecto.pia_2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.proyecto.pia_2.controller.EmpleadoController;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.proyecto.pia_2.model.Empleado;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmpleadoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EmpleadoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        Empleado empleado = new Empleado("manuel", "chele@example.com", "1234");
        empleado.setUsuario_id(1L);
    }

    @Test
    public void EmpleadoController_Guardar() throws Exception {
        String json = """
                {
                       "usuario_id":"1",
                       "username":"manuel",
                       "email":"chele@example.com",
                       "password":"1234"
                }
                """;
        when(empleadoRepository.save(any(Empleado.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/addEmpleado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    public void EmpleadoController_Eliminar() throws Exception {
        when(empleadoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(empleadoRepository).deleteById(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/eliminarEmpleado/{id}",1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(empleadoRepository, times(1)).deleteById(1L);
    }


}
