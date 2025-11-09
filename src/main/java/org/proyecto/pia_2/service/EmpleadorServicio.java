package org.proyecto.pia_2.service;

import org.proyecto.pia_2.dto.EmpleadorRegistroDTO;
import org.proyecto.pia_2.model.Empleador;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmpleadorServicio extends UserDetailsService {
    public Empleador save(EmpleadorRegistroDTO empleadorRegistroDTO);
    public List<Empleador> listarEmpleadores();
    public Empleador getUsername(String username);
    public Empleador getEmail(String email);
}
