package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.model.*;
import org.example.backend.repository.EmpleadorRepository;
import org.example.backend.repository.TareaRepository;
import org.example.backend.service.impl.EmpleadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/empleador")
public class EmpleadorController {


    private final EmpleadorServiceImpl empleadorServiceimpl;


    private final TareaRepository tareaRepository;

    @Autowired
    public EmpleadorController(EmpleadorServiceImpl empleadorServiceimpl, TareaRepository tareaRepository){
        this.empleadorServiceimpl = empleadorServiceimpl;
        this.tareaRepository = tareaRepository;
    }

    @PostMapping("/agregar")
    ResponseEntity<Empleador> agregar(@RequestBody @Valid Empleador empleador){
        Empleador empleador1 = empleadorServiceimpl.registrarEmpleador(empleador);
        return new ResponseEntity<>(empleador1, HttpStatus.CREATED);
    }


    @PostMapping("/addTarea/{id}")
    ResponseEntity<Tarea> agregarTarea(@RequestBody @Valid Tarea tarea, @PathVariable Long id){
        Tarea tareaRegistrada = empleadorServiceimpl.agregarTarea(tarea, id);
        return new ResponseEntity<>(tareaRegistrada, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarTarea(@PathVariable Long id){
        tareaRepository.deleteById(id);
    }



}
