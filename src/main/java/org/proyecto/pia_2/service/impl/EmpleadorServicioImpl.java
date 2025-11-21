package org.proyecto.pia_2.service.impl;

import org.proyecto.pia_2.dto.EmpleadorRegistroDTO;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.proyecto.pia_2.service.EmpleadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadorServicioImpl implements EmpleadorServicio, UserDetailsService {
    @Autowired
    private EmpleadorRepository empleadorRepository;

    public EmpleadorServicioImpl(EmpleadorRepository empleadorRepository) {
        this.empleadorRepository = empleadorRepository;
    }

    @Override
    @Transactional
    public Empleador save(EmpleadorRegistroDTO registroDTO) {
        Empleador empleador = new Empleador(registroDTO.getUsername(), registroDTO.getEmail(), registroDTO.getPassword());
        return empleadorRepository.save(empleador);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleador empleador = empleadorRepository.findByEmail(username);
        if (empleador == null) {
            empleador = empleadorRepository.findByUsername(username);
        }
        if (empleador == null) {
            throw new UsernameNotFoundException("Usuario o password incorrectos.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(username, empleador.getPassword(), grantedAuthorities);
    }

    @Override
    public List<Empleador> listarEmpleadores() {
        return empleadorRepository.findAll();
    }

    public Empleador getUsername(String username) {
        return empleadorRepository.findByUsername(username);
    }

    public Empleador getEmail(String email) {
        return empleadorRepository.findByEmail(email);
    }
}
