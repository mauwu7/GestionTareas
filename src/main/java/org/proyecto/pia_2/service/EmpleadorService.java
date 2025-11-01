package org.proyecto.pia_2.service;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.exception.UsuarioRegistradoException;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;

import java.util.List;

public interface EmpleadorService {

    Empleador agregarEntornoTrabajo(EntornoTrabajo entornoTrabajo, Long id) throws UsuarioNotFoundException;

    void agregarEmpleador(Empleador empleador) throws UsuarioRegistradoException;

    Empleador GetEmpleador(Long id) throws UsuarioNotFoundException;

    Empleador EditarEmpleador(Empleador empleador, Long id) throws UsuarioNotFoundException;

    List<Empleador> obtenerEmpleadores();

    void EliminarEmpleador(Long id) throws UsuarioNotFoundException;

    List<EntornoTrabajo> obtenerEntornoTrabajos(Long id) throws  UsuarioNotFoundException;
}
