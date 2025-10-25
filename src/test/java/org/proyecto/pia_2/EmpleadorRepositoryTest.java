package org.proyecto.pia_2;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmpleadorRepositoryTest {

    @Autowired
    private EmpleadorRepository empleadorRepository;

    Empleador empleador;
    EntornoTrabajo entornoTrabajo;
    @BeforeEach
    void setUp() {
        empleador = new Empleador("nombre","empleado@gmail.com","19282");
        entornoTrabajo = new EntornoTrabajo("Equipo Fantastico", "Un equipo bueno",empleador);
        empleador.getEntornosDeTrabajo().add(entornoTrabajo);
        empleadorRepository.save(empleador);
    }

    @Test
    void testInsertion(){
        Empleador saveduser = empleadorRepository.findById(empleador.getUsuario_id()).orElse(null);
        assertNotNull(saveduser);
        assertEquals(empleador.getUsuario_id(),saveduser.getUsuario_id());
        assertEquals(empleador.getEmail(),saveduser.getEmail());
    }

    @Test
    void ActualizarUsuarioTest(){
        empleador.setEmail("lolero@gmail.com");
        empleadorRepository.save(empleador);
        Empleador saveduser = empleadorRepository.findById(empleador.getUsuario_id()).orElse(null);
        assertNotNull(saveduser);
        assertEquals(empleador.getEmail(),saveduser.getEmail());

    }

    @Test
    @Transactional
    void RelacionUsuariosEntornoTrabajoTest(){
        Empleador empleadorTest = empleadorRepository.findById(empleador.getUsuario_id()).orElse(null);

        List<EntornoTrabajo> entornoTrabajoTest = empleadorTest.getEntornosDeTrabajo();//PUEDE GENERAR UNA EXCEPCION
        assertNotNull(entornoTrabajoTest);

        EntornoTrabajo entornoTrabajoTestRelacion = entornoTrabajoTest.get(0);
        assertEquals(empleador.getUsuario_id(), entornoTrabajoTestRelacion.getEmpleador().getUsuario_id());

    }

    @Test
    void testFindByUsername(){
        Empleador saveduser = empleadorRepository.findByUsername(empleador.getUsername());
        assertNotNull(saveduser);
        assertEquals(empleador.getUsername(),saveduser.getUsername());
    }

    @Test
    void EliminarUsuarioTest(){
        empleadorRepository.deleteById(empleador.getUsuario_id());
        assertFalse(empleadorRepository.findById(empleador.getUsuario_id()).isPresent());

    }

    @AfterEach
    void tearDown() {
        empleadorRepository.deleteAll();
    }



}
