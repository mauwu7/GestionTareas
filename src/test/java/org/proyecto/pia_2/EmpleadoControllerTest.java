package org.proyecto.pia_2;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.proyecto.pia_2.controller.EmpleadoController;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.proyecto.pia_2.model.Empleado;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmpleadoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@JsonFormat(pattern = "yyyy-MM-dd")
public class EmpleadoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpleadoRepository empleadoRepository;

    private Empleado empleado;

    @BeforeEach
    public void setUp() {
        empleado = new Empleado("manuel", "chele@example.com", "1234");
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

        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteEmpleado/{id}",1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        verify(empleadoRepository,times(1)).deleteById(1L);
    }

    @Test
    public void EmpleadoController_Actualizar() throws Exception {
        String edit = """
                {
                       "username":"mauedit",
                       "email":"putoEditado@gmail.com",
                       "password":"1234edit"
                }
                """;
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        when(empleadoRepository.save(any(Empleado.class))).thenAnswer(i -> i.getArgument(0));
        mockMvc.perform(MockMvcRequestBuilders.put("/editarEmpleado/{id}",1L).contentType(MediaType.APPLICATION_JSON)
                        .content(edit)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
        verify(empleadoRepository,times(1)).findById(1L);
        verify(empleadoRepository,times(1)).save(any(Empleado.class));

    }

    @Test
    public void EmpleadoController_AgregarTareasTest() throws Exception {
        String jsonTarea = """
                {
                       "tarea_id":"1",
                       "descripcion":"ejemplo",
                       "fechaVencimiento":"2025-11-01",
                       "fechaCreacion":"2025-10-31",
                       "prioridad":"1",
                       "estado":"PENDIENTE"
                }
                """;
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        mockMvc.perform(MockMvcRequestBuilders.put("/agregarTareas/{idEmpleado}",1L)
                .contentType(MediaType.APPLICATION_JSON).content(jsonTarea).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        verify(empleadoRepository,times(1)).findById(1L);

    }

    @Test
    public void EmpleadorController_GetTest() throws Exception {
        Empleado empleado2 = new Empleado("mau", "mau@gmail.com", "15kilos");
        empleado2.setUsuario_id(2L);

        List<Empleado> empleados = Arrays.asList(empleado, empleado2);
        when(empleadoRepository.findAll()).thenReturn(empleados);

        mockMvc.perform(MockMvcRequestBuilders.get("/getEmpleados")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        verify(empleadoRepository,times(1)).findAll();

    }
}


