package org.proyecto.pia_2.service;
import org.proyecto.pia_2.exception.*;
import org.proyecto.pia_2.model.*;

import java.time.LocalDate;
import java.util.List;

public interface EmpleadorService {

    Empleador agregarEntornoTrabajo(EntornoTrabajo entornoTrabajo, Long id) throws UsuarioNotFoundException, EquipoRegistradoException;

    Empleador agregarEmpleador(Empleador empleador) throws UsuarioRegistradoException;

    Empleador GetEmpleador(Long id) throws UsuarioNotFoundException;

    Empleador EditarEmpleador(Empleador empleador, Long id) throws UsuarioNotFoundException, UsuarioRegistradoException;

    List<Empleador> obtenerEmpleadores();

    void EliminarEmpleador(Long id) throws UsuarioNotFoundException;

    List<EntornoTrabajo> obtenerEntornoTrabajos(Long id) throws  UsuarioNotFoundException; //Obtener los entornos de trabajo de un empleador

    //Consultas sobre los entornos de Trabajo-----------------------------------
    EntornoTrabajo AgregarEquipoEnEntornoDeTrabajo(Equipo equipo, String nombreEntornoDeTrabajo) throws EquipoRegistradoException, EquipoNotFoundException;

    EntornoTrabajo EditarEntorno(EntornoTrabajo entornoTrabajoEditado, String nombreEntornoDeTrabajo) throws  EquipoNotFoundException, EquipoRegistradoException;

    void EliminarEntornoDeTrabajo(String nombre) throws EquipoNotFoundException;

    //Consultas sobre los Equipos-----------------------------------
    Equipo AgregarEmpleadoaEquipo(Empleado empleado, String nombreDeEquipo) throws EquipoNotFoundException, UsuarioRegistradoException;

    Equipo EditarEquipo(Equipo equipoEditado, String nombreEquipo) throws EquipoRegistradoException, EquipoNotFoundException;

    void eliminarEquipo(String nombreDeEquipo) throws EquipoNotFoundException;

    //Consultas sobre los empleados y tareas
    TareaIndividual AgregarTarea(TareaIndividual tareaIndividual, String nombreEmpleado) throws UsuarioNotFoundException;

    void editarPrioridadTarea(Long idTarea, Integer prioridad, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException;

    void editarfechaVencimiento(Long idTarea, LocalDate fechaVencimiento, String nombreEmpleado) throws UsuarioNotFoundException,  TareaNotFoundException;

    void EliminarTarea(Long idTarea, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException;
}
