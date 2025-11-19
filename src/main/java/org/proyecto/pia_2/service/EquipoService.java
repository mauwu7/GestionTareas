package org.proyecto.pia_2.service;
import org.proyecto.pia_2.exception.EquipoNotFoundException;
import org.proyecto.pia_2.exception.EquipoRegistradoException;
import org.proyecto.pia_2.exception.UsuarioRegistradoException;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.Equipo;

import java.util.List;

public interface EquipoService {

    List<Empleado> ConsultarEmpleadosEnEquipo(Long idEquipo) throws EquipoNotFoundException;

    Equipo AgregarEmpleado(Empleado empleado, String nombreEquipo) throws EquipoNotFoundException, UsuarioRegistradoException;

    Equipo EditarEquipo(Equipo equipo,  String nombreEquipo) throws EquipoNotFoundException, EquipoRegistradoException;

    void EliminarEquipo(String nombreEquipo)throws EquipoNotFoundException;
}
