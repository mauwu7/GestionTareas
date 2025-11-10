package org.proyecto.pia_2.service;
import org.proyecto.pia_2.exception.TareaNotFoundException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.model.TareaIndividual;

import java.time.LocalDate;

public interface TareaService {

    TareaIndividual AgregarTarea(TareaIndividual tarea, String nombreEmpleado) throws UsuarioNotFoundException;

    void EditarPrioridadTarea(Long id, Integer prioridad, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException;

    void EditarFechaVencimiento(Long id, LocalDate fechaVencimiento, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException;

    void FinalizarTarea(Long id, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException;

}
