package org.proyecto.pia_2.controller;
import jakarta.validation.Valid;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.service.impl.EmpleadorServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmpleadorController {

    private final EmpleadorServiceimpl empleadorService;
    @Autowired
    public EmpleadorController(EmpleadorServiceimpl empleadorService){
        this.empleadorService = empleadorService;
    }

    @PostMapping("/addEmpleador")
    ResponseEntity<Empleador> guardarEmpleador(@RequestBody @Valid Empleador empleador){
        empleadorService.agregarEmpleador(empleador);
        return new ResponseEntity<Empleador>(empleador, HttpStatus.CREATED);
    }

    @PutMapping("/agregarEntornos/{idEmpleador}")
    ResponseEntity<Empleador> agregarEntornosEmpleadores(@RequestBody @Valid EntornoTrabajo entornoTrabajo, @PathVariable Long idEmpleador) throws UsuarioNotFoundException{
        Empleador empleador = empleadorService.agregarEntornoTrabajo(entornoTrabajo, idEmpleador);
        return new ResponseEntity<Empleador>(empleador,HttpStatus.OK);
    }
    @GetMapping("/requestEmpleador")
    public List<Empleador> obtenerEmpleadores(){
        return empleadorService.obtenerEmpleadores();
    }

    @PutMapping("/editarEmpleador/{id}")
    ResponseEntity<Empleador> EditarEmpleador(@RequestBody @Valid Empleador empleador, @PathVariable Long id) throws UsuarioNotFoundException {
        Empleador empleadorEditado = empleadorService.EditarEmpleador(empleador, id);
        return new ResponseEntity<Empleador>(empleadorEditado,HttpStatus.OK);
    }

    @DeleteMapping("/eliminarEmpleador/{id}")
    public void eliminarEmpleador(@PathVariable Long id) throws UsuarioNotFoundException{
        empleadorService.EliminarEmpleador(id);
    }



}
