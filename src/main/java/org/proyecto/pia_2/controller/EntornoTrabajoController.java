package org.proyecto.pia_2.controller;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.proyecto.pia_2.exception.EquipoNotFoundException;
import org.proyecto.pia_2.model.Equipo;
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
public class EntornoTrabajoController {

    /*private final EntornoTrabajoServiceImpl entornoTrabajoService;

    @Autowired
    public EntornoTrabajoController(EntornoTrabajoServiceImpl entornoTrabajoService) {
        this.entornoTrabajoService = entornoTrabajoService;
    }

    //--check
    @GetMapping("/consultarEquipos/{entornoId}")
    ResponseEntity<List<Equipo>> ConsultarEquipos(@PathVariable @NotNull @Min(1) Long entornoId) throws EquipoNotFoundException {
        return new ResponseEntity<>(entornoTrabajoService.ConsultarEquipos(entornoId), HttpStatus.FOUND);
    }*/

}
