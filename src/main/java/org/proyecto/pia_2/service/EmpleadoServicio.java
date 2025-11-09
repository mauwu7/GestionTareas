package org.proyecto.pia_2.service;

import org.proyecto.pia_2.model.Empleado;
import java.util.List;

public interface EmpleadoServicio {
    List<Empleado> listarEmpleados();
    List<Empleado> obtenerEmpleadosSinEquipo();
    Empleado obtenerEmpleadoPorId(Long id);
    Empleado crearEmpleado(String username, String password, String nombre, Long equipoId);
    void asignarEmpleadoAEquipo(Long empleadoId, Long equipoId);
    void removerEmpleadoDeEquipo(Long empleadoId);
    Empleado actualizarEmpleado(Long id, String username);
    void eliminarEmpleado(Long id);
}