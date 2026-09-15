package org.example.backend.service;

import org.example.backend.DTO.LoginDTO;
import org.example.backend.DTO.TokenDTO;
import org.example.backend.DTO.UserDTO;

public interface AuthService {

    UserDTO registrarEmpleador(UserDTO userDTO);

    TokenDTO loginUsuario(LoginDTO loginDTO);


}
