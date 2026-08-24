package org.example.backend.service.impl;

import org.example.backend.DTO.UserDTO;
import org.example.backend.exception.GrupoNotFoundException;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.Empleado;
import org.example.backend.model.Grupo;
import org.example.backend.model.RolNombre;
import org.example.backend.repository.EmpleadoRepository;

import org.example.backend.repository.GrupoRepository;
import org.example.backend.repository.UsuarioRepository;
import org.example.backend.service.GrupoService;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;


    @Autowired
    public GrupoServiceImpl(GrupoRepository grupoRepository,PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository){
        this.grupoRepository = grupoRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDTO agregarEmpleadoGrupo(Long id, UserDTO userDTO) throws GrupoNotFoundException, UserNotFoundException {
        Grupo grupo = grupoRepository.findById(id).orElseThrow(()->new GrupoNotFoundException("Grupo not founded"));
        if(usuarioRepository.existsByEmail(userDTO.getEmail())){
            throw new UserNotFoundException("User not found");
        }
        else{
            Empleado empleado = new Empleado(userDTO.getEmail(), userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), RolNombre.USER,userDTO.getName());
            empleado.setId_grupo(grupo);
            grupo.getMiembros().add(empleado);
            grupoRepository.save(grupo);
            return userDTO;
        }
    }
}
