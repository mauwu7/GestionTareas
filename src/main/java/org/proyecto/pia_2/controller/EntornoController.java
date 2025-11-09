package org.proyecto.pia_2.controller;

import org.proyecto.pia_2.model.*;
import org.proyecto.pia_2.repository.EntornoTrabajoRepository;
import org.proyecto.pia_2.service.EntornoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/entornosTrabajo")
public class EntornoController {
    private final EntornoTrabajoRepository entornoRepository;
    @Autowired
    private final EntornoServicio entornoServicio;

    @Autowired
    public EntornoController(EntornoTrabajoRepository entornoRepository, EntornoServicio entornoServicio) {
        this.entornoRepository = entornoRepository;
        this.entornoServicio = entornoServicio;
    }

    /*@PostMapping("/addEntorno")
    public ResponseEntity<EntornoTrabajo> addEntorno(@RequestBody EntornoTrabajo entorno){
        entornoRepository.save(entorno);
        return new ResponseEntity<>(entorno, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntornoTrabajo> getEntornoById(@PathVariable Long id){
        Optional<EntornoTrabajo> entorno = entornoRepository.findById(id);
        return entorno.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleteEntorno/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        entornoRepository.deleteById(id);
    }

    @PutMapping("/addEmpleador/{id}")
    public ResponseEntity<EntornoTrabajo> agregarEmpleador(@RequestBody Empleador empleador, @PathVariable Long id){
        EntornoTrabajo entorno = entornoRepository.findById(id).orElse(null);
        entorno.setEmpleador(empleador); // Todo: excepcion
        entornoRepository.save(entorno);
        return new ResponseEntity<>(entorno,HttpStatus.OK);
    }

    @PutMapping("/addEquipo/{id}")
    public ResponseEntity<EntornoTrabajo> agregarEquipo(@RequestBody Equipo equipo, @PathVariable Long id){
        EntornoTrabajo entorno = entornoRepository.findById(id).orElse(null);
        entorno.getEquiposEntornos().add(equipo); // Todo: excepcion
        entornoRepository.save(entorno);
        return new ResponseEntity<>(entorno,HttpStatus.OK);
    }

    @PutMapping("/editarEntorno/{id}")
    public ResponseEntity<EntornoTrabajo> editEntorno(@RequestBody EntornoTrabajo entorno, @PathVariable Long id){
        EntornoTrabajo entornoEdit = entornoRepository.findById(id).orElse(null);
        entornoEdit.setNombre(entorno.getNombre());
        entornoEdit.setDescripcion(entorno.getDescripcion());
        entornoRepository.save(entornoEdit);

        return new ResponseEntity<>(entornoEdit,HttpStatus.OK);
    }*/



}
