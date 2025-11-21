package org.proyecto.pia_2.service.impl;

import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.repository.EntornoTrabajoRepository;
import org.proyecto.pia_2.service.EntornoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntornoServicioImpl implements EntornoServicio {
    @Autowired
    private EntornoTrabajoRepository entornoRepository;

    public List<EntornoTrabajo> listarEntornosTrabajo(){
        return entornoRepository.findAll();
    }

    public EntornoTrabajo crearEntornoTrabajo(String nombre, String descripcion, Empleador empleador) {
        EntornoTrabajo entorno = new EntornoTrabajo();
        entorno.setNombre(nombre);
        entorno.setDescripcion(descripcion);
        entorno.setEmpleador(empleador);
        return entornoRepository.save(entorno);
    }

    public EntornoTrabajo obtenerEntornoPorId(Long id) {
        return entornoRepository.findById(id).orElse(null);
    }

    public EntornoTrabajo actualizarEntorno(Long id, String nombre, String descripcion) {
        EntornoTrabajo entorno = obtenerEntornoPorId(id);
        if (entorno != null) {
            entorno.setNombre(nombre);
            entorno.setDescripcion(descripcion);
            return entornoRepository.save(entorno);
        }
        throw new RuntimeException("Entorno no encontrado con ID: " + id);
    }

    public void eliminarEntorno(Long id) {
        entornoRepository.deleteById(id);
    }
}