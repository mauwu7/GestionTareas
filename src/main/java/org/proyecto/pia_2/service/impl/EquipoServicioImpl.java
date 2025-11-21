package org.proyecto.pia_2.service.impl;

import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.model.Equipo;
import org.proyecto.pia_2.repository.EntornoTrabajoRepository;
import org.proyecto.pia_2.repository.EquipoRepository;
import org.proyecto.pia_2.service.EquipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServicioImpl implements EquipoServicio {
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private EntornoTrabajoRepository entornoTrabajoRepository;

    @Override
    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    @Override
    public Equipo crearEquipo(String nombre, Long id_entorno) {
        Equipo equipo = new Equipo();
        EntornoTrabajo entornoTrabajo = entornoTrabajoRepository.findById(id_entorno).orElse(null);
        equipo.setNombreEquipo(nombre);
        equipo.setEntornoTrabajo(entornoTrabajo);
        return equipoRepository.save(equipo);
    }

    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }

    @Override
    public Equipo actualizarEquipo(Long id, String nombre) {
        Equipo equipo = obtenerEquipoPorId(id);
        if (equipo != null) {
            equipo.setNombreEquipo(nombre);
            return equipoRepository.save(equipo);
        }
        throw new RuntimeException("Equipo no encontrado con ID: " + id);
    }


}
