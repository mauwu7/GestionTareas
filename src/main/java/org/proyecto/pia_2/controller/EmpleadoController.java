package org.proyecto.pia_2.controller;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.proyecto.pia_2.exception.TareaNotFoundException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.TareaIndividual;
import org.proyecto.pia_2.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/consultarTareas/{idEmpleado}") //-check
    ResponseEntity<List<TareaIndividual>> ConsultarTareas (@PathVariable @NotNull @Min(1)Long idEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {
        return new ResponseEntity<>(empleadoService.ConsultarTareas(idEmpleado), HttpStatus.FOUND);
    }

    @GetMapping("/consultarInformacion/{idEmpleado}")//-check
    ResponseEntity<Empleado> ConsultarInformacion (@PathVariable @NotNull @Min(1) Long idEmpleado) throws UsuarioNotFoundException{
        return new  ResponseEntity<>(empleadoService.ConsultarInformacion(idEmpleado), HttpStatus.FOUND);
    }

    @DeleteMapping("/finalizarTarea/{nombreEmpleado}/{idTarea}")//
    void CompletarTarea(@PathVariable @NotBlank String nombreEmpleado, @PathVariable @NotNull @Min(1) Long idTarea) throws UsuarioNotFoundException, TareaNotFoundException {
        empleadoService.completarTarea(nombreEmpleado,  idTarea);
    }


}
