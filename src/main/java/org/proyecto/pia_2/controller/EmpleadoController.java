package org.proyecto.pia_2.controller;
import jakarta.validation.constraints.NotNull;
import org.proyecto.pia_2.exception.TareaNotFoundException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.model.TareaIndividual;
import org.proyecto.pia_2.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Validated
public class EmpleadoController {

    EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("consultarTareas/{idEmpleado}")
    ResponseEntity<List<TareaIndividual>> ConsultarTareas (@PathVariable @NotNull Long idEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {
        return new ResponseEntity<>(empleadoService.ConsultarTareas(idEmpleado), HttpStatus.FOUND);
    }

}
