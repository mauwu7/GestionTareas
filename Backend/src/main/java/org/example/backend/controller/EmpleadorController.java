package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.model.*;
import org.example.backend.service.impl.EmpleadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class EmpleadorController {


    private final EmpleadorServiceImpl empleadorServiceimpl;

    @Autowired
    public EmpleadorController(EmpleadorServiceImpl empleadorServiceimpl){
        this.empleadorServiceimpl = empleadorServiceimpl;
    }

    @PostMapping("/agregar")
    ResponseEntity<Empleador> agregar(@RequestBody @Valid Empleador empleador){
        Empleador empleador1 = empleadorServiceimpl.registrarEmpleador(empleador);
        return new ResponseEntity<>(empleador1, HttpStatus.CREATED);
    }




}
