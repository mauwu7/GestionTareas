package org.example.backend.service;

import org.example.backend.DTO.UserDTO;
import org.example.backend.exception.GrupoNotFoundException;
import org.example.backend.model.Empleado;

public interface GrupoService {

    UserDTO agregarEmpleadoGrupo(Long id, UserDTO userDTO);

}
