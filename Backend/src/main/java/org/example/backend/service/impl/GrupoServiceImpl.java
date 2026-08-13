package org.example.backend.service.impl;

import org.example.backend.exception.GrupoNotFoundException;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.Empleado;
import org.example.backend.model.Grupo;
import org.example.backend.repository.EmpleadoRepository;

import org.example.backend.repository.GrupoRepository;
import org.example.backend.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;
    private final EmpleadoRepository empleadoRepository;


    @Autowired
    public GrupoServiceImpl(GrupoRepository grupoRepository, EmpleadoRepository empleadoRepository){
        this.grupoRepository = grupoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Empleado agregarEmpleadoGrupo(Long id, Empleado empleado) throws GrupoNotFoundException, UserNotFoundException {
        Grupo grupo = grupoRepository.findById(id).orElseThrow(()->new GrupoNotFoundException("Grupo not founded"));
        if(empleadoRepository.existsByEmail(empleado.getEmail())){
            throw new UserNotFoundException("User not found");
        }
        else{
            empleado.setId_grupo(grupo);
            grupo.getMiembros().add(empleado);
            grupoRepository.save(grupo);
            return empleado;
        }
    }
}
