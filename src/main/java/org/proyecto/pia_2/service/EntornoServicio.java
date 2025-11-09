package org.proyecto.pia_2.service;

import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EntornoServicio {
    public List<EntornoTrabajo> listarEntornosTrabajo();
    public EntornoTrabajo crearEntornoTrabajo(String nombre, String descripcion, Empleador empleador);
    public void eliminarEntorno(Long id);
    EntornoTrabajo actualizarEntorno(Long id, String nombre, String descripcion);
    EntornoTrabajo obtenerEntornoPorId(Long id);
}
