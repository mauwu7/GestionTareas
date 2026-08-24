package org.example.backend.controller;


import jakarta.validation.Valid;
import org.example.backend.DTO.UserDTO;

import org.example.backend.model.Grupo;
import org.example.backend.service.impl.EmpleadorServiceImpl;
import org.example.backend.service.impl.GrupoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    private final GrupoServiceImpl grupoServiceimpl;
    private final EmpleadorServiceImpl empleadorServiceimpl;

    @Autowired
    public GrupoController(GrupoServiceImpl grupoServiceimpl, EmpleadorServiceImpl empleadorServiceimpl){
        this.empleadorServiceimpl = empleadorServiceimpl;
        this.grupoServiceimpl = grupoServiceimpl;
    }

    @GetMapping("/{id}")
    ResponseEntity<List<Grupo>> getGrupos(@PathVariable Long id){
        List<Grupo> grupos = empleadorServiceimpl.gruposEmpleador(id);
        return new ResponseEntity<>(grupos, HttpStatus.FOUND);
    }

    @PostMapping("/agregar/{id}")
    ResponseEntity<Grupo> agregarGrupo(@PathVariable Long id, @RequestBody @Valid Grupo grupo){
        Grupo grupo1 = empleadorServiceimpl.agregarGrupo(grupo, id);
        return new ResponseEntity<>(grupo1, HttpStatus.CREATED);
    }

    @PostMapping("/agregarEmp/{id}")
    ResponseEntity<UserDTO> agregarEmpleadoGrupo(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(grupoServiceimpl.agregarEmpleadoGrupo(id, userDTO), HttpStatus.CREATED);
    }

}
