package org.proyecto.pia_2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proyecto.pia_2.controller.EmpleadorController;
import org.proyecto.pia_2.exception.EquipoNotFoundException;
import org.proyecto.pia_2.exception.EquipoRegistradoException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.exception.UsuarioRegistradoException;
import org.proyecto.pia_2.exception.handler.GlobalHandlerException;
import org.proyecto.pia_2.model.*;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Se integrara en este clase el test del servicio de empleador
@WebMvcTest(value= EmpleadorController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalHandlerException.class)
public class EmpleadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmpleadorRepository empleadorRepository;

    @MockBean
    EmpleadorServiceimpl empleadorService;

    @BeforeEach
    public void setUp() {
        //Empleador empleador = new Empleador("PrimerEmpleador", "primerEmpleado@gmail.com", "123456e10");
        //empleador.setUsuario_id(1L);
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
        when(empleadorService.agregarEmpleador(any(Empleador.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/addEmpleador")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void UsuarioRegistradoExceptionTest() throws Exception {

        String json = """
                {
                       "usuario_id":"1",
                       "username":"nuevoEmpleador",
                       "email":"empleador@gmail.com",
                       "password":"algunacontraseña"
                }
                """;


        when(empleadorService.agregarEmpleador(any(Empleador.class))).thenThrow(new UsuarioRegistradoException("El usuario ya se encuentra registrado"));

        mockMvc.perform(MockMvcRequestBuilders.post("/addEmpleador").
                contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict()).andDo(print());
    }

    @Test
    public void MethodArgumentNotValidExceptionTest() throws Exception{

        String json = """
                {
                       "usuario_id":"1",
                       "username":"n",
                       "email": "e",
                       "password":"1213114"
                }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/addEmpleador")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void ConstraintViolationExceptionTest() throws Exception{
        String json = """
                {
                "entorno_id":"",
                "nombre":"EquipoMaravilloso",
                "descripcion":"Un equipo dedicado a ser maravilloso"
                }
                """;

        mockMvc.perform((MockMvcRequestBuilders.post("/agregarEntornos/{idEmpleador}",-2L))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void EquipoRegistradoException() throws Exception{

        String json = """
                {
                "entorno_id":"1",
                "nombre":"EquipoMaravilloso",
                "descripcion":"Un equipo dedicado a ser maravilloso"
                }
                """;

        when(empleadorService.agregarEntornoTrabajo(any(EntornoTrabajo.class),eq(1L))).thenThrow(new EquipoRegistradoException("El equipo ya se encuentra registrado"));

        mockMvc.perform(MockMvcRequestBuilders.post("/agregarEntornos/{idEmpleador}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isConflict()).andDo(print());

        verify(empleadorService,times(1)).agregarEntornoTrabajo(any(EntornoTrabajo.class),eq(1L));

    }

    @Test
    public void EliminarEmpleadorAndThrowUsuarioNotFoundExceptionTest() throws Exception{
        String json = """
                {
                       "usuario_id":"1",
                       "username":"n",
                       "email": "e",
                       "password":"1213114"
                }
        """;
        doThrow(new UsuarioNotFoundException("No hay ningun usuario registrado con ese ID")).when(empleadorService).EliminarEmpleador(eq(1L));

        mockMvc.perform(MockMvcRequestBuilders.delete("/eliminarEmpleador/{idEmpleador}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andDo(print());

    }

    @Test
    public void AgregarEquipoEnEntornoDeTrabajoTestAndThrowsEquipoNotFoundException() throws Exception {
        String json = """
                {
                    "equipo_id":"1",
                    "nombreEquipo":"EquipoMaravilloso"
                }
                """;

        when(empleadorService.AgregarEquipoEnEntornoDeTrabajo(any(Equipo.class),anyString())).thenThrow(new EquipoNotFoundException("No se encuentra ningun equipo registrado con ese nombre"));

        mockMvc.perform(MockMvcRequestBuilders.post("/agregarEquipos/{nombreEntorno}","Entorno de TRbajo")
                .contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    public void AgregarTareaEnEmpleado()throws Exception{ //Para poder pasar este test hay que redefinir el valor que devuelve el metodo
                                                            //agregar tarea en el servicio y el controlador

        String json = """
                {
                "tarea_id":"1",
                "descripcion":"Una tarea que se ha registrado",
                "fechaVencimiento":"2007-12-03",
                "fechaCreacion":"2007-12-10",
                "prioridad":"3",
                "estado":"PENDIENTE",
                "etiquetas":["buenas","tardes"]
                }
                """;

        when(empleadorService.AgregarTarea(any(TareaIndividual.class),anyString())).thenAnswer(i-> i.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/agregarTarea/{nombreEmpleado}","Javier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void EditarTareasEnEmpleado()throws Exception{ //Solo se prueba que no genere algun problema.Es indiferente lo que regrese el mock
        String json= """
                {
                "fechaVencimiento":"2007-12-29",
                "prioridad":"2",
                "estado":"EN PROGRESO"
                }
                """;

        Empleado empleado = new Empleado("ajsjaj","jajja@gmail.com","jasssa22");

        //when(empleadorService.EditarTarea(any(TareaDTO.class),eq(1L),anyString())).thenReturn(empleado);


        mockMvc.perform(MockMvcRequestBuilders.put("/editarTarea/{idTarea}/{nombreEmpleado}",1L,"Javier")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
    }




    /*

    Estos test van a dejar de usar empleadorRepository para usar el servicio de empleador

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
    */

}