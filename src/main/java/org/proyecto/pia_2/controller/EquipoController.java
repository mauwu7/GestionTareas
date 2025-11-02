package org.proyecto.pia_2.controller;

import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.Equipo;
import org.proyecto.pia_2.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EquipoController {
    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoController(EquipoRepository equipoRepository){this.equipoRepository = equipoRepository;}

    @PostMapping("/addEquipo")
    public ResponseEntity<Equipo> addEquipo(@RequestBody Equipo equipo){
        equipoRepository.save(equipo);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEquipo/{id}")
    public void deleteEquipo(@PathVariable Long id){
        equipoRepository.deleteById(id);
    }

    @GetMapping("/getEquipos")
    public List<Equipo> getEquipos(){
        return equipoRepository.findAll();
    }

    @PutMapping("/editarEquipo")
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


}
