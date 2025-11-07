package org.proyecto.pia_2.controller;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleadores")
public class EmpleadorController {

    private final EmpleadorRepository empleadorRepository;
    @Autowired
    public EmpleadorController(EmpleadorRepository empleadorRepository) {
        this.empleadorRepository = empleadorRepository;
    }

    @PostMapping("/addEmpleador")
    ResponseEntity<Empleador> guardarEmpleador(@RequestBody Empleador empleador){
        empleadorRepository.save(empleador);
        return new ResponseEntity<Empleador>(empleador, HttpStatus.CREATED);
    }

    @GetMapping("/{id:\\d+}")
    ResponseEntity<Empleador> buscarEmpleadorPorId(@PathVariable Long id){
        Optional<Empleador> empleador = empleadorRepository.findById(id);
        return empleador.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/agregarEntornos/{idEmpleador}")
    ResponseEntity<Empleador> agregarEntornosEmpleadores(@RequestBody EntornoTrabajo entornoTrabajo, @PathVariable Long idEmpleador){
        Empleador empleador = empleadorRepository.findById(idEmpleador).orElse(null);
        empleador.getEntornosDeTrabajo().add(entornoTrabajo); //Añadir Excepcion
        empleadorRepository.save(empleador);
        return new ResponseEntity<Empleador>(empleador,HttpStatus.OK);
    }
    @GetMapping("/requestEmpleador")
    public List<Empleador> obtenerEmpleadores(){
        return empleadorRepository.findAll();
    }

    @PutMapping("/editarEmpleador/{id}")
    ResponseEntity<Empleador> EditarEmpleador(@RequestBody Empleador empleador, @PathVariable Long id){
        Empleador empleadorEditado = empleadorRepository.findById(id).orElse(null);
        empleadorEditado.setEmail(empleador.getEmail()); // AÑADIR EXCEPCION
        empleadorEditado.setUsername(empleador.getUsername());
        empleadorEditado.setPassword(empleador.getPassword());
        empleadorRepository.save(empleadorEditado);
        return new ResponseEntity<Empleador>(empleadorEditado,HttpStatus.OK);
    }

    @DeleteMapping("/eliminarEmpleador/{id}")
    public void eliminarEmpleador(@PathVariable Long id){
        empleadorRepository.deleteById(id);
    }

}
