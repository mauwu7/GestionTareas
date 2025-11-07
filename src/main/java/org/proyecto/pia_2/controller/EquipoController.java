package org.proyecto.pia_2.controller;

import org.apache.coyote.Response;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.model.Equipo;
import org.proyecto.pia_2.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoController(EquipoRepository equipoRepository){this.equipoRepository = equipoRepository;}

    @PostMapping("/addEquipo")
    public ResponseEntity<Equipo> addEquipo(@RequestBody Equipo equipo){
        equipoRepository.save(equipo);
        return new ResponseEntity<>(equipo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Equipo> buscarEquipoPorId(@PathVariable Long id){
        Optional<Equipo> equipo = equipoRepository.findById(id);
        return equipo.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleteEquipo/{id}")
    public void deleteEquipo(@PathVariable Long id){
        equipoRepository.deleteById(id);
    }

    @GetMapping("/getEquipos")
    public List<Equipo> getEquipos(){
        return equipoRepository.findAll();
    }

    @PutMapping("/editarEquipo/{id}")
    public ResponseEntity<Equipo> editEquipo(@RequestBody Equipo equipo, @PathVariable Long id){
        Equipo equipoEdit = equipoRepository.findById(id).orElse(null);
        if(equipoEdit == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        equipoEdit.setNombreEquipo(equipo.getNombreEquipo());
        equipoEdit.setEntornoTrabajo(equipo.getEntornoTrabajo());
        equipoEdit.setEmpleados(equipo.getEmpleados());
        return new ResponseEntity<>(equipoRepository.save(equipoEdit), HttpStatus.OK);
    }

    @PutMapping("/agregarEntornoEquipo/{idEquipo}")
    public ResponseEntity<Equipo> asignarEntornoTrabajo(@RequestBody EntornoTrabajo entornoTrabajo, @PathVariable Long idEquipo){
        Equipo equipo = equipoRepository.findById(idEquipo).orElse(null);
        equipo.setEntornoTrabajo(entornoTrabajo);
        equipoRepository.save(equipo);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    @PutMapping("/agregarEmpleadoEquipo/{idEquipo}")
    public ResponseEntity<Equipo> asignarEmpleadoEquipo(@RequestBody Empleado empleado, @PathVariable Long idEquipo){
        Equipo equipo = equipoRepository.findById(idEquipo).orElse(null);
        equipo.getEmpleados().add(empleado);
        equipoRepository.save(equipo);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }
}
