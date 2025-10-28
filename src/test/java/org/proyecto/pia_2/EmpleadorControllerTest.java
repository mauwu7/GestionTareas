package org.proyecto.pia_2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proyecto.pia_2.controller.EmpleadorController;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value= EmpleadorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmpleadorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    EmpleadorRepository empleadorRepository;

    private Empleador empleador;

    @BeforeEach
    public void setUp() {
        empleador = new Empleador("PrimerEmpleador","primerEmpleado@gmail.com","123456e10");
        empleador.setUsuario_id(1L);
    }

    @Test
    public void PostEmpleadoTest() throws Exception{
        String json = """
                {
                       "usuario_id":"1",
                       "username":"nuevoEmpleador",
                       "email":"empleador@gmail.com",
                       "password":"algunacontraseña"
                }
                """;

        when(empleadorRepository.save(any(Empleador.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/addEmpleador")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());

        verify(empleadorRepository,times(1)).save(any(Empleador.class));
    }

    @Test public void AgregarEntornosTrabajoTest() throws Exception{

        String jsonEntornoTrabjo=
                """
                {
                "entorno_id":"1",
                "nombre":"Equipo Fantastico",
                "descripcion":"Equipo Fantastico"
                }
                """;

        when(empleadorRepository.findById(1L)).thenReturn(Optional.of(empleador));

        mockMvc.perform(MockMvcRequestBuilders.put("/agregarEntornos/{idEmpleador}",1L)
        .contentType(MediaType.APPLICATION_JSON).content(jsonEntornoTrabjo).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        verify(empleadorRepository,times(1)).findById(1L);

    }

    @Test public void ObtenerEmpleadoresRegistrados() throws Exception{
        Empleador empleador2 = new Empleador("Empleador2","empleador2@gmail.com","842824831f");
        empleador2.setUsuario_id(2L);

        List<Empleador> empleadores = Arrays.asList(empleador,empleador2);

        when(empleadorRepository.findAll()).thenReturn(empleadores);

        mockMvc.perform(MockMvcRequestBuilders.get("/requestEmpleador")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        verify(empleadorRepository,times(1)).findAll();

    }

    @Test public void EliminarEmpleador() throws Exception{

        when(empleadorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(empleadorRepository).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/eliminarEmpleador/{id}",1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        verify(empleadorRepository,times(1)).deleteById(1L);
    }

    @Test public void EditarEmpleador() throws Exception{

        String jsonEditado = """
                {
                       "username":"AlgunEmpleadorEditado",
                       "email":"empleadorEditado@gmail.com",
                       "password":"algunacontraseñaEditada"
                }
                """;

        when(empleadorRepository.findById(1L)).thenReturn(Optional.of(empleador));
        when(empleadorRepository.save(any(Empleador.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.put("/editarEmpleador/{id}",1L).contentType(MediaType.APPLICATION_JSON)
                .content(jsonEditado)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());


        verify(empleadorRepository,times(1)).findById(1L);
        verify(empleadorRepository,times(1)).save(any(Empleador.class));

    }

}