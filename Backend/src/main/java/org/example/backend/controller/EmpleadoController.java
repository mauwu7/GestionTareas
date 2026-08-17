package org.example.backend.controller;


import org.example.backend.DTO.TareaDTO;
import org.example.backend.model.Tarea;
import org.example.backend.service.impl.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    private final EmpleadoServiceImpl empleadoServiceimpl;

    @Autowired
    public EmpleadoController(EmpleadoServiceImpl empleadoServiceimpl){
        this.empleadoServiceimpl = empleadoServiceimpl;
    }

    @DeleteMapping("/{id}")
    public void finalizarTarea(@PathVariable Long id){
        empleadoServiceimpl.finalizarTarea(id);
    }

    @GetMapping("/tareas/{id}")
    ResponseEntity<TareaDTO> tareasEmpleado(@PathVariable Long id){
        return new ResponseEntity<>(empleadoServiceimpl.getTareasEmpleado(id), HttpStatus.FOUND);
    }





}
