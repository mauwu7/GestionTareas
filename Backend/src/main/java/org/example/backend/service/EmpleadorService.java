package org.example.backend.service;


import org.example.backend.exception.UserNotFoundException;
import org.example.backend.exception.UserRegisteredException;

import org.example.backend.model.Empleador;
import org.example.backend.model.Grupo;
import org.example.backend.model.Tarea;

import java.util.List;

public interface EmpleadorService {

    Empleador registrarEmpleador(Empleador empleador);

    List<Grupo> gruposEmpleador(Long id);

    Grupo agregarGrupo(Grupo grupo, Long id);

    Tarea agregarTarea(Tarea tarea, Long id);



}
