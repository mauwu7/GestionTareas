package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.exception.UserRegisteredException;
import org.example.backend.model.*;
import org.example.backend.repository.EmpleadoRepository;
import org.example.backend.repository.UsuarioRepository;
import org.example.backend.service.impl.EmpleadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.backend.repository.EmpleadorRepository;
import org.example.backend.repository.GrupoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiEmpl")
public class EmpleadorController {

    private final EmpleadorRepository empleadorRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;

    private final EmpleadorServiceImpl empleadorServiceimpl;

    @Autowired
    public EmpleadorController(EmpleadorRepository empleadorRepository, GrupoRepository grupoRepository, UsuarioRepository usuarioRepository, EmpleadoRepository empleadoRepository, EmpleadorServiceImpl empleadorServiceimpl){
        this.empleadorRepository = empleadorRepository;
        this.grupoRepository = grupoRepository;
        this.usuarioRepository = usuarioRepository;
        this.empleadoRepository = empleadoRepository;
        this.empleadorServiceimpl = empleadorServiceimpl;
    }


    @PostMapping("/agregar")
    ResponseEntity<Empleador> agregar(@RequestBody @Valid Empleador empleador){
        Empleador empleador1 = empleadorServiceimpl.registrarEmpleador(empleador);
        return new ResponseEntity<>(empleador1, HttpStatus.CREATED);
    }

    @GetMapping("/grupos/{id}")
    ResponseEntity<List<Grupo>> getGrupos(@PathVariable Long id){
        List<Grupo> grupos = empleadorServiceimpl.gruposEmpleador(id);
        return new ResponseEntity<>(grupos, HttpStatus.FOUND);
    }

    @PostMapping("/grupos/agregar{id}")
    ResponseEntity<Grupo> agregarGrupo(@PathVariable Long id, @RequestBody @Valid Grupo grupo){
        Grupo grupo1 = empleadorServiceimpl.agregarGrupo(grupo, id);
        return new ResponseEntity<>(grupo1, HttpStatus.CREATED);
    }







    @GetMapping("/{id}")
    ResponseEntity<Empleador> getEmpleadores(@PathVariable Long id){
        Optional<Empleador> empleador = empleadorRepository.findById(id);
        return empleador.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/del/{id}")
    HttpStatus deleteEmpleador(@PathVariable Long id){
        empleadorRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }


    @PostMapping("/addGrupo/{id}")
    ResponseEntity<Empleador> agregarGrupoEmpleador(@PathVariable Long id, @RequestBody Grupo grupo){
        Empleador empleador = empleadorRepository.findById(id).orElse(null);
        grupo.setId_empleador(empleador);
        empleador.getGrupos().add(grupo);
        return new ResponseEntity<>(empleadorRepository.save(empleador), HttpStatus.CREATED);
    }

    @GetMapping("/getGrupos")
    ResponseEntity<List<Grupo>> getGrupos(){
        return new ResponseEntity<>(grupoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addUser/{id}")
    ResponseEntity<Grupo> agregarEmpleadoGrupo(@PathVariable Long id, @RequestBody Empleado empleado){
        Grupo grupo = grupoRepository.findById(id).orElse(null);
        empleado.setId_grupo(grupo);
        grupo.getMiembros().add(empleado);
        return new ResponseEntity<>(grupoRepository.save(grupo), HttpStatus.CREATED);
    }

    @GetMapping("/getUsuarios")
    ResponseEntity<List<Usuario>> getUsuarios(){return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.FOUND);}

    @PostMapping("/addTarea/{id}")
    ResponseEntity<Empleado> agregarTareaEmpleado(@PathVariable Long id, @RequestBody Tarea tarea){
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        tarea.setEmpleado_id(empleado);
        tarea.toString();
        empleado.getTareas().add(tarea);
        return new ResponseEntity<>(empleadoRepository.save(empleado), HttpStatus.CREATED);
    }

    @DeleteMapping("delEmp/{id}")
    ResponseEntity<String> deleteEmpleado (@PathVariable Long id){
        empleadoRepository.deleteById(id);
        return new ResponseEntity<>("Eliminado", HttpStatus.NO_CONTENT);
    }

}
