package org.example.backend.service;


import org.example.backend.DTO.TareaDTO;

public interface EmpleadoService {

    void finalizarTarea(Long id) ;

    TareaDTO getTareasEmpleado(Long id);
}
