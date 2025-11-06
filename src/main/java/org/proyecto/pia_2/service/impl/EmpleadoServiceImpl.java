package org.proyecto.pia_2.service.impl;

import org.proyecto.pia_2.exception.TareaNotFoundException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.TareaIndividual;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.proyecto.pia_2.service.EmpleadoService;
import org.proyecto.pia_2.service.PlanificadorTareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

//Revisar despues en los otros metodos que las listas si tengan una implementacion
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<TareaIndividual> ConsultarTareas(Long idEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {
        Empleado empledoConsultado = empleadoRepository.findById(idEmpleado).orElseThrow(() -> new UsuarioNotFoundException("No se encontro ningun usuario con id: "+idEmpleado));
        if(empledoConsultado.getTareasAsignadas().isEmpty()){
            throw new TareaNotFoundException("El usuario no tiene niguna tareas asignada");
        }
        else{
            List<TareaIndividual> tareas = empledoConsultado.getTareasAsignadas();
            tareas.sort(new PlanificadorTareas());
            return tareas;
        }
    }
}

