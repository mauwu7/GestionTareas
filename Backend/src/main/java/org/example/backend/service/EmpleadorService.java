package org.example.backend.service;

import org.example.backend.exception.UserNotFoundException;
import org.example.backend.exception.UserRegisteredException;
import org.example.backend.model.Empleador;
import org.example.backend.model.Grupo;

import java.util.List;

public interface EmpleadorService {

    Empleador registrarEmpleador(Empleador empleador) throws UserRegisteredException;

    List<Grupo> gruposEmpleador(Long id) throws UserNotFoundException;

    Grupo agregarGrupo(Grupo grupo, Long id) throws UserNotFoundException;

}
