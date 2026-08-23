package org.example.backend.service;

import org.example.backend.DTO.LoginDTO;
import org.example.backend.DTO.UserDTO;

public interface AuthService {

    UserDTO registrarEmpleador(UserDTO userDTO);

    LoginDTO loginUsuario(LoginDTO loginDTO);

    UserDTO registrarEmpleado(UserDTO userDTO, Long id);

}
