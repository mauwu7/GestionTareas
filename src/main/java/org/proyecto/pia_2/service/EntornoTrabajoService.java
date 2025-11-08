package org.proyecto.pia_2.service;
import org.proyecto.pia_2.exception.EquipoNotFoundException;
import org.proyecto.pia_2.exception.EquipoRegistradoException;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.model.Equipo;
import java.util.List;

public interface EntornoTrabajoService {

    List<Equipo> ConsultarEquipos(Long idEntorno) throws EquipoNotFoundException;

    Empleador CrearEntornoTrabajo(EntornoTrabajo entornoTrabajo, Empleador empleador) throws EquipoRegistradoException;

    EntornoTrabajo AgregarEquipo(Equipo equipo, String nombreEntornoDeTrabajo) throws EquipoRegistradoException, EquipoNotFoundException;

    EntornoTrabajo EditarEntornoDeTrabajo(EntornoTrabajo entornoTrabajo, String nombreEntornoDeTrabajo) throws EquipoRegistradoException, EquipoNotFoundException;

    void EliminarEntornoDeTrabajo(String nombreEntornoDeTrabajo) throws EquipoNotFoundException;
}
