package org.example.backend.service.impl;


import org.example.backend.DTO.LoginDTO;
import org.example.backend.DTO.UserDTO;
import org.example.backend.exception.UserRegisteredException;
import org.example.backend.model.Empleador;
import org.example.backend.model.RolNombre;
import org.example.backend.repository.EmpleadoRepository;
import org.example.backend.repository.EmpleadorRepository;
import org.example.backend.repository.UsuarioRepository;
import org.example.backend.security.JwtService;

import org.example.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmpleadoRepository empleadoRepository;
    private final EmpleadorRepository empleadorRepository;
    private final UsuarioDetailsService usuarioDetailsService;
    private final UsuarioRepository usuarioRepository;


    @Autowired
    public AuthServiceImpl(UsuarioRepository usuarioRepository ,UsuarioDetailsService usuarioDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService, EmpleadorRepository empleadorRepository, EmpleadoRepository empleadoRepository){
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.empleadorRepository = empleadorRepository;
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDTO registrarEmpleador(UserDTO userDTO) {
        if(usuarioRepository.existsByEmail(userDTO.getEmail())){
            throw new UserRegisteredException("Ingresar un email diferente");
        }
        else{
            Empleador empleador = new Empleador(userDTO.getEmail(), userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), RolNombre.ADMIN, userDTO.getName());
            empleadorRepository.save(empleador);
            return userDTO;
        }
    }

    @Override
    public LoginDTO loginUsuario(LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        UserDetails userDetails = usuarioDetailsService.loadUserByUsername(loginDTO.getEmail());
        String token = jwtService.generateToken(userDetails);
        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    public UserDTO registrarEmpleado(UserDTO userDTO, Long id) {
        return null;
    }
}
