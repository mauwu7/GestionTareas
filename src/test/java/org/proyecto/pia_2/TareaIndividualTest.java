package org.proyecto.pia_2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proyecto.pia_2.model.TareaIndividual;
import org.proyecto.pia_2.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TareaIndividualTest {

    @Autowired
    private TareaRepository tareaRepository;

    TareaIndividual tareaIndividual;

    @BeforeEach
    public void setUp(){
        tareaIndividual = new TareaIndividual("Tarea pendiente para el plazo xx", LocalDate.parse("2025-06-03"),LocalDate.parse("2025-06-12"),1,"PENDIENTE");
        tareaIndividual.getEtiquetas().add("Administracion");
        tareaIndividual.getEtiquetas().add("Contabilidad");
        tareaIndividual.getEtiquetas().add("Sub-departamento");
        tareaRepository.save(tareaIndividual);
    }

    @Test
    public void InsertionTest(){
        TareaIndividual tareaIndividualTest = tareaRepository.findById(tareaIndividual.getTarea_id()).orElse(null);
        assertNotNull(tareaIndividualTest);
        assertEquals(tareaIndividual.getTarea_id(),tareaIndividualTest.getTarea_id());
    }

    @Test
    public void EtiquestasTareaTest(){
        TareaIndividual tareaIndividualTest = tareaRepository.findById(tareaIndividual.getTarea_id()).orElse(null);
        List<String> etiquetas = tareaIndividualTest.getEtiquetas(); //Puede generar excepcion
        assertNotNull(etiquetas);
        assertEquals(etiquetas.get(0),"Administracion");
    }

    @AfterEach
    public void tearDown(){
        tareaRepository.deleteAll();
    }



}
