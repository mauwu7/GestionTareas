package org.proyecto.pia_2.controller;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.proyecto.pia_2.exception.EquipoRegistradoException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.exception.UsuarioRegistradoException;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.service.impl.EmpleadorServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class EmpleadorController {

    private final EmpleadorServiceimpl empleadorService;
    @Autowired
    public EmpleadorController(EmpleadorServiceimpl empleadorService){
        this.empleadorService = empleadorService;
    }

    @PostMapping("/addEmpleador")
    ResponseEntity<Empleador> guardarEmpleador(@RequestBody @Valid Empleador empleador) throws UsuarioRegistradoException{
        return new ResponseEntity<Empleador>(empleadorService.agregarEmpleador(empleador), HttpStatus.CREATED);
    }

    @PutMapping("/agregarEntornos/{idEmpleador}")
    ResponseEntity<Empleador> agregarEntornosEmpleadores(@RequestBody @Valid EntornoTrabajo entornoTrabajo, @PathVariable @Min(1) @NotNull Long idEmpleador) throws EquipoRegistradoException, UsuarioNotFoundException{
        Empleador empleador = empleadorService.agregarEntornoTrabajo(entornoTrabajo, idEmpleador);
        return new ResponseEntity<Empleador>(empleador,HttpStatus.OK);
    }
    @GetMapping("/requestEmpleador")
    public List<Empleador> obtenerEmpleadores(){
        return empleadorService.obtenerEmpleadores();
    }

    @PutMapping("/editarEmpleador/{id}")
    ResponseEntity<Empleador> EditarEmpleador(@RequestBody @Valid Empleador empleador, @PathVariable @Min(1) @NotNull Long id) throws UsuarioNotFoundException, UsuarioRegistradoException {
        Empleador empleadorEditado = empleadorService.EditarEmpleador(empleador, id);
        return new ResponseEntity<Empleador>(empleadorEditado,HttpStatus.OK);
    }

    @DeleteMapping("/eliminarEmpleador/{id}")
    public void eliminarEmpleador(@PathVariable @Min(1) @NotNull Long id) throws UsuarioNotFoundException{
        empleadorService.EliminarEmpleador(id);
    }



}
