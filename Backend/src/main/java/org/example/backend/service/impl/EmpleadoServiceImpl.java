package org.example.backend.service.impl;

import org.example.backend.DTO.TareaDTO;

import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.Empleado;
import org.example.backend.model.Tarea;
import org.example.backend.repository.EmpleadoRepository;
import org.example.backend.repository.TareaRepository;
import org.example.backend.service.EmpleadoService;
import org.example.backend.service.PlanificadorTareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final TareaRepository tareaRepository;
    private final EmpleadoRepository empleadoRepository;


    @Autowired
    public EmpleadoServiceImpl(TareaRepository tareaRepository, EmpleadoRepository empleadoRepository){
        this.tareaRepository = tareaRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public void finalizarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public TareaDTO getTareasEmpleado(Long id) {
        TareaDTO tareaDTO = new TareaDTO();
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not found"));
        List<Tarea> planificador = empleado.getTareas();
        planificador.sort(new PlanificadorTareas());
        tareaDTO.setPlanificadorTareas(planificador);

        return tareaDTO;
    }

    public List<Tarea> tareasEmpleado(Long id){
        return tareaRepository.findTareaByIdEmpleadorOrdenada(id);
    }


}
