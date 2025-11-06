package org.proyecto.pia_2.service;
import org.proyecto.pia_2.exception.TareaNotFoundException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.model.TareaIndividual;

import java.util.List;

public interface EmpleadoService {
    List<TareaIndividual>  ConsultarTareas (Long idEmpleado) throws UsuarioNotFoundException, TareaNotFoundException;
}
