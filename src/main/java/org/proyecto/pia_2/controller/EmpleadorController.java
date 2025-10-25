package org.proyecto.pia_2.controller;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmpleadorController {

    private EmpleadorRepository empleadorRepository;
    @Autowired
    public EmpleadorController(EmpleadorRepository empleadorRepository) {
        this.empleadorRepository = empleadorRepository;
    }

    @PostMapping("/addEmpleador")
    Empleador guardarEmpleador(@RequestBody Empleador empleador){
        return empleadorRepository.save(empleador);
    }

    @PostMapping("/agregarEntornos/{idEmpleador}")
    Empleador agregarEntornosEmpleadores(@RequestBody EntornoTrabajo entornoTrabajo, @PathVariable Long idEmpleador){
        Empleador empleador = empleadorRepository.findById(idEmpleador).orElse(null);
        empleador.getEntornosDeTrabajo().add(entornoTrabajo);
        return empleadorRepository.save(empleador);
    }
    @GetMapping("/requestEmpleador")
    public List<Empleador> obtenerEmpleadores(){
        return empleadorRepository.findAll();
    }

    @PutMapping("/editarEmpleador/{id}")
    Empleador EditarEmpleador(@RequestBody Empleador empleador, @PathVariable Long id){
        Empleador empleadorEditado = empleadorRepository.findById(id).orElse(null);
        empleadorEditado.setEmail(empleador.getEmail());
        return empleadorRepository.save(empleadorEditado);
    }

    @DeleteMapping("/eliminarEmpleador/{id}")
    public void eliminarEmpleador(@PathVariable Long id){
        empleadorRepository.deleteById(id);
    }





}
