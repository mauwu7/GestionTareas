package org.proyecto.pia_2.service;
import org.proyecto.pia_2.exception.EmpleadorNotFoundException;
import org.proyecto.pia_2.exception.EntornoTrabajoException;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;

import java.util.List;

public interface EmpleadorService {

    Empleador agregarEntornoTrabajo(EntornoTrabajo entornoTrabajo, Long id) throws EntornoTrabajoException;

    void agregarEmpleador(Empleador empleador);

    Empleador GetEmpleador(Long id) throws EmpleadorNotFoundException;

    Empleador EditarEmpleador(Empleador empleador, Long id) throws EmpleadorNotFoundException;

    List<Empleador> obtenerEmpleadores();

}
