package org.example.backend.service.impl;

import org.example.backend.DTO.TareaDTO;

import org.example.backend.repository.EmpleadoRepository;
import org.example.backend.repository.TareaRepository;
import org.example.backend.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        tareaDTO.setTareasOrden(tareaRepository.findTareaByIdEmpleadoOrdenada(id));
        tareaDTO.setPlanificadorTareas(tareaRepository.findTareaByEmpleado_idOrderByPrioridadAscFechaVencimientoDesc(id));
        return tareaDTO;

    }




}
