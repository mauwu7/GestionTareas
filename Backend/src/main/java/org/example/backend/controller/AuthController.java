package org.example.backend.controller;

import org.example.backend.DTO.LoginDTO;
import org.example.backend.DTO.UserDTO;
import org.example.backend.model.Empleador;
import org.example.backend.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @Autowired
    public AuthController(AuthServiceImpl authServiceImpl){
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping("/registro/empleador")
    public ResponseEntity<UserDTO> registroEmpleador(@RequestBody UserDTO userDTO){
         return new ResponseEntity<>(authServiceImpl.registrarEmpleador(userDTO), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginDTO> loginUser(@RequestBody LoginDTO loginDTO){
        return new ResponseEntity<>(authServiceImpl.loginUsuario(loginDTO), HttpStatus.ACCEPTED);
    }

}
