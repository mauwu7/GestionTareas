package org.proyecto.pia_2.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.proyecto.pia_2.model.Equipo;
import org.proyecto.pia_2.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EquipoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EquipoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EquipoRepository equipoRepository;

    private Equipo equipo;
    @BeforeEach
    public void setUp() {
        equipo = new Equipo("cheleros");
        equipo.setEquipo_id(1L);
    }

    @Test
    public void testAddEquipo() throws Exception {
        String json = """
                {
                       "equipo_id":"1",
                       "nombreEquipo":"cheleros"
                }
                """;
        when(equipoRepository.save(any(Equipo.class))).thenAnswer(i -> i.getArgument(0));
        mockMvc.perform(MockMvcRequestBuilders.post("/addEquipo").
                contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
        verify(equipoRepository, times(1)).save(any(Equipo.class));
    }
    @Test
    public void testDeleteEquipo() throws Exception {
        when(equipoRepository.existsById(1L)).thenAnswer(i -> i.getArgument(0));
        doNothing().when(equipoRepository).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteEquipo/{id}",1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(equipoRepository,times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateEquipo() throws Exception {
        String edit = """
                {
                    "nombreEquipo":"chelerosEdit"
                }
                """;

        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));
        when(equipoRepository.save(any(Equipo.class))).thenAnswer(i -> i.getArgument(0));
        mockMvc.perform(MockMvcRequestBuilders.put("/editarEquipo/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(edit).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(equipoRepository,times(1)).findById(1L);
        verify(equipoRepository,times(1)).save(any(Equipo.class));
    }

    @Test
    public void testGetEquipo() throws Exception {
        Equipo equipo2 = new Equipo("cheleros2");
        equipo2.setEquipo_id(2L);
        List<Equipo> equipos = Arrays.asList(equipo, equipo2);

        when(equipoRepository.findAll()).thenReturn(equipos);

        mockMvc.perform(MockMvcRequestBuilders.get("/getEquipos").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
        verify(equipoRepository,times(1)).findAll();
    }

    @Test
    public void testAgregarEntornoTrabajo() throws Exception {
        String jsonEntorno=
                """
                {
                "entorno_id":"1",
                "nombre":"Equipo Chelero",
                "descripcion":"Equipo Chelero"
                }
                """;

        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));

        mockMvc.perform(MockMvcRequestBuilders.put("/agregarEntornoEquipo/{idEquipo}",1L)
                .contentType(MediaType.APPLICATION_JSON).content(jsonEntorno).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        verify(equipoRepository,times(1)).findById(1L);

    }

    @Test
    public void testAgregarEmpleado() throws Exception {
        String jsonEmpleado = """
                {
                    "usuario_id":"1",
                    "username":"mau",
                    "email":"smen@example.com",
                    "password":"123456"
                }
                """;
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));
        mockMvc.perform(MockMvcRequestBuilders.put("/agregarEmpleadoEquipo/{idEquipo}",1L)
                .contentType(MediaType.APPLICATION_JSON).content(jsonEmpleado).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        verify(equipoRepository,times(1)).findById(1L);

    }
}

