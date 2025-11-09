package org.proyecto.pia_2.service;

import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.model.Equipo;

import java.util.List;

public interface EquipoServicio {
    public List<Equipo> listarEquipos();
    public Equipo crearEquipo(String nombre, Long id_empleador);
    public void eliminarEquipo(Long id);
    Equipo actualizarEquipo(Long id, String nombre);
    Equipo obtenerEquipoPorId(Long id);
}
