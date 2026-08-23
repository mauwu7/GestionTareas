package org.example.backend.service.impl;


import org.example.backend.model.Usuario;
import org.example.backend.repository.UsuarioRepository;

import org.example.backend.security.UsuarioDetails;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioDetailsService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email).orElseThrow(()->new UsernameNotFoundException("Usuario no encotrado"));
        return new UsuarioDetails(usuario);
    }



}
