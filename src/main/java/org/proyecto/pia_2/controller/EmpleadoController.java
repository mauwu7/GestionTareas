package org.proyecto.pia_2.controller;

import org.apache.coyote.Response;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.TareaIndividual;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @PostMapping("/addEmpleado")
    ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado) {
        empleadoRepository.save(empleado);
        return new ResponseEntity<Empleado>(empleado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable Long id){
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleteEmpleado/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        empleadoRepository.deleteById(id);
    }

    @PutMapping("/agregarTareas/{idEmpleado}")
    public ResponseEntity<Empleado> agregarTareas(@RequestBody TareaIndividual tarea, @PathVariable Long idEmpleado){
        Empleado empleado = empleadoRepository.findById(idEmpleado).orElse(null);
        empleado.getTareasAsignadas().add(tarea); // Todo: excepcion
        empleadoRepository.save(empleado);
        return new ResponseEntity<Empleado>(empleado,HttpStatus.OK);
    }

    @GetMapping("/getEmpleados")
    public List<Empleado> getEmpleados(){
        return empleadoRepository.findAll();
    }

    @PutMapping("/editarEmpleado/{id}")
    public ResponseEntity<Empleado> editEmpleados(@RequestBody Empleado empleado, @PathVariable Long id){
        Empleado empleadoEditado = empleadoRepository.findById(id).orElse(null);
        empleadoEditado.setUsername(empleado.getUsername());
        empleadoEditado.setEmail(empleado.getEmail());
        empleadoEditado.setPassword(empleado.getPassword());
        empleadoRepository.save(empleadoEditado);

        return new ResponseEntity<Empleado>(empleadoEditado,HttpStatus.OK);
    }
}

