package org.example.backend.service;

import org.example.backend.exception.GrupoNotFoundException;
import org.example.backend.model.Empleado;

public interface GrupoService {

    Empleado agregarEmpleadoGrupo(Long id, Empleado empleado);

}
