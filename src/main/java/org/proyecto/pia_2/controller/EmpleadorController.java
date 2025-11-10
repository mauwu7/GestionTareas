package org.proyecto.pia_2.controller;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.proyecto.pia_2.exception.*;
import org.proyecto.pia_2.model.*;
import org.proyecto.pia_2.service.impl.EmpleadorServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class EmpleadorController {

    private final EmpleadorServiceimpl empleadorService;

    @Autowired
    public EmpleadorController(EmpleadorServiceimpl empleadorService){
        this.empleadorService = empleadorService;
    }

    //Operaciones generales sobre empleador-----------------------------------------------

    @PostMapping("/addEmpleador") //Este metodo es momentaneo
    ResponseEntity<Empleador> guardarEmpleador(@RequestBody @Valid Empleador empleador) throws UsuarioRegistradoException{
        return new ResponseEntity<>(empleadorService.agregarEmpleador(empleador), HttpStatus.CREATED);
    }

    @GetMapping("/requestEmpleador") //Este tambien
    public List<Empleador> obtenerEmpleadores(){
        return empleadorService.obtenerEmpleadores();
    }

    //-check
    @PutMapping("/editarEmpleador/{id}")
    ResponseEntity<Empleador> EditarEmpleador(@RequestBody @Valid Empleador empleador, @PathVariable @Min(1) @NotNull Long id) throws UsuarioNotFoundException, UsuarioRegistradoException {
        Empleador empleadorEditado = empleadorService.EditarEmpleador(empleador, id);
        return new ResponseEntity<>(empleadorEditado,HttpStatus.OK);
    }

    @DeleteMapping("/eliminarEmpleador/{id}")
    public void eliminarEmpleador(@PathVariable @Min(1) @NotNull Long id) throws UsuarioNotFoundException{
        empleadorService.EliminarEmpleador(id);
    }

    //Operaciones en los entornos de Trabajo----------------------------------------

    @PostMapping("/agregarEquipos/{nombreEntorno}")
    ResponseEntity<EntornoTrabajo> agregarEquipoAEntornoDeTrabajo(@RequestBody @Valid Equipo equipoAgregado, @PathVariable @NotBlank String nombreEntorno) throws EquipoRegistradoException {
        return new ResponseEntity<> (empleadorService.AgregarEquipoEnEntornoDeTrabajo(equipoAgregado, nombreEntorno), HttpStatus.CREATED);
    }

    @PostMapping("/agregarEntornos/{idEmpleador}")
    ResponseEntity<Empleador> agregarEntornosEmpleadores(@RequestBody @Valid EntornoTrabajo entornoTrabajo, @PathVariable @Min(1) @NotNull Long idEmpleador) throws EquipoRegistradoException, UsuarioNotFoundException{
        Empleador empleador = empleadorService.agregarEntornoTrabajo(entornoTrabajo, idEmpleador);
        return new ResponseEntity<>(empleador,HttpStatus.CREATED);
    }

    @PutMapping("/editarEntornodeTrabajo/{nombreEntorno}") //Considerar añadir el id del empleador que tiene asignado ese entorno de trabajo
    ResponseEntity<EntornoTrabajo> EditarEntornoDeTrabajo(@RequestBody @Valid EntornoTrabajo entornoTrabajo, @PathVariable @NotBlank String nombreEntorno) throws EquipoNotFoundException, EquipoRegistradoException{
        return new ResponseEntity<>(empleadorService.EditarEntorno(entornoTrabajo,nombreEntorno),HttpStatus.OK);
    }

    @DeleteMapping("/eliminarEntornodeTrabajo/{nombreEntorno}")
    void EliminarEntornoDeTrabajo(@PathVariable @NotBlank String nombreEntorno){
        empleadorService.EliminarEntornoDeTrabajo(nombreEntorno);
    }

    //Operaciones en los Equipos---------------------------------------------------


    @PostMapping("/agregarEmpleado/{nombreEquipo}")
    ResponseEntity<Equipo> agregarEmpleadoEnEquipo(@RequestBody @Valid Empleado empleadoAgregado, @PathVariable @NotBlank String nombreEquipo){
        return new ResponseEntity<>(empleadorService.AgregarEmpleadoaEquipo(empleadoAgregado,nombreEquipo),HttpStatus.CREATED);
    }

    @PutMapping("/editarEquipo/{nombreEquipo}")
    ResponseEntity<Equipo> EditarEquipo(@RequestBody @Valid Equipo equipoEditado, @PathVariable @NotBlank String nombreEquipo) throws EquipoRegistradoException, EquipoNotFoundException{
        return new ResponseEntity<>(empleadorService.EditarEquipo(equipoEditado, nombreEquipo),HttpStatus.OK);
    }

    @DeleteMapping("/eliminarEquipo/{nombreEquipo}")
    void EliminarEquipo(@PathVariable @NotBlank String nombreEquipo) throws EquipoNotFoundException{
        empleadorService.eliminarEquipo(nombreEquipo);
    }

    //Operaciones en los empleados y las tareas--------------------------------------------------
    @PostMapping("/agregarTarea/{nombreEmpleado}")
    ResponseEntity<TareaIndividual> agregarTareaEnEmpleado(@RequestBody @Valid  TareaIndividual tarea, @PathVariable @NotBlank String nombreEmpleado) throws UsuarioNotFoundException{
        return new ResponseEntity<>(empleadorService.AgregarTarea(tarea, nombreEmpleado),HttpStatus.CREATED);
    }

    @PatchMapping("editarPrioridad/{idTarea}/{prioridad}/{nombreEmpleado}")
    void editarTareaPrioridad(@Min(1) @Max(3) @NotNull @PathVariable Integer prioridad, @PathVariable @NotBlank String nombreEmpleado, @PathVariable @Min(1) @NotNull Long idTarea) throws UsuarioNotFoundException, TareaNotFoundException{
        empleadorService.editarPrioridadTarea(idTarea, prioridad, nombreEmpleado);
    }

    @PatchMapping("/editarFecha/{fechaVencimiento}/{idTarea}/{nombreEmpleado}")
    void editarTareaFechaVencimiento(@NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate fechaVencimiento, @Min(1) @NotNull @PathVariable Long idTarea,  @NotNull @PathVariable String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException{
        empleadorService.editarfechaVencimiento(idTarea, fechaVencimiento, nombreEmpleado);
    }

    @DeleteMapping("/finalizarTarea/{idTarea}/{nombreEmpleado}")
    void eliminarTarea(@PathVariable @Min(1) @NotNull Long idTarea, @PathVariable @NotBlank String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException{
        empleadorService.EliminarTarea(idTarea, nombreEmpleado);
    }

}
