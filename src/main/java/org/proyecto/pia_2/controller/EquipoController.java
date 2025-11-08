package org.proyecto.pia_2.controller;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.proyecto.pia_2.exception.EquipoNotFoundException;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.service.impl.EquipoServiceImpl;
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
public class EquipoController {

    private final EquipoServiceImpl equipoService;

    @Autowired
    public EquipoController(EquipoServiceImpl equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping("/consultarEmpleados/{idEquipo}")
    ResponseEntity<List<Empleado>> ConsultarEquipos(@PathVariable @Min(1) @NotNull Long idEquipo) throws EquipoNotFoundException {
        return new ResponseEntity<>(equipoService.ConsultarEmpleadosEnEquipo(idEquipo), HttpStatus.FOUND);
    }


}
