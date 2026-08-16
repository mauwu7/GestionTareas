package org.example.backend.service.impl;

import org.example.backend.exception.GrupoRegisteredException;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.exception.UserRegisteredException;

import org.example.backend.model.Empleado;
import org.example.backend.model.Empleador;
import org.example.backend.model.Grupo;
import org.example.backend.model.Tarea;
import org.example.backend.repository.EmpleadoRepository;
import org.example.backend.repository.EmpleadorRepository;
import org.example.backend.repository.GrupoRepository;
import org.example.backend.service.EmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class EmpleadorServiceImpl implements EmpleadorService {

    private final EmpleadorRepository empleadorRepository;
    private final GrupoRepository grupoRepository;
    private final EmpleadoRepository empleadoRepository;


    public Empleador auxFinder(Long id){
        return empleadorRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Autowired
    public  EmpleadorServiceImpl(EmpleadorRepository empleadorRepository, GrupoRepository grupoRepository, EmpleadoRepository empleadoRepository){
        this.empleadorRepository = empleadorRepository;
        this.grupoRepository = grupoRepository;
        this.empleadoRepository = empleadoRepository;
    }



    //MOVER ESTE METODO
    @Override
    public Empleador registrarEmpleador(Empleador empleador){
        if(empleadorRepository.existsByEmail(empleador.getEmail())){
            throw new UserRegisteredException("El email ya se encuentra registrado");
        }
        else{
            return empleadorRepository.save(empleador);
        }
    }

    @Override
    public List<Grupo> gruposEmpleador(Long id){
        Empleador empleador = auxFinder(id);
        return empleador.getGrupos();
    }

    @Override
    public Grupo agregarGrupo(Grupo grupo, Long id){
        Empleador empleador = auxFinder(id);
        if(grupoRepository.existsByNombreGrupo(grupo.getNombreGrupo())){
            throw new GrupoRegisteredException("Ya se ha registrado un grupo con ese nombre");
        }
        grupo.setId_empleador(empleador);
        empleador.getGrupos().add(grupo);
        empleadorRepository.save(empleador);
        return grupo;
    }

    @Override
    public Tarea agregarTarea(Tarea tarea, Long id){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not found"));
        tarea.setEmpleado_id(empleado);
        empleado.getTareas().add(tarea);
        empleadoRepository.save(empleado);
        return tarea;
    }


}
