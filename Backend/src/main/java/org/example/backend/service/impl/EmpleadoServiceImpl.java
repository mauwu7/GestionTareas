package org.example.backend.service.impl;

import org.example.backend.DTO.TareaDTO;

import org.example.backend.repository.TareaRepository;
import org.example.backend.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final TareaRepository tareaRepository;


    @Autowired
    public EmpleadoServiceImpl(TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    @Override
    public void finalizarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public TareaDTO getTareasEmpleado(Long id) {
        TareaDTO tareaDTO = new TareaDTO();
        return tareaDTO;
    }


}
