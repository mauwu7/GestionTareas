package org.proyecto.pia_2.controller.page_controllers;

import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.service.EmpleadorServicio;
import org.proyecto.pia_2.service.EntornoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {
    @Autowired
    EntornoServicio entornoServicio;
    @Autowired
    EmpleadorServicio empleadorServicio;

    @GetMapping("/")
    public String paginaInicio(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        model.addAttribute("usuario", username);
        model.addAttribute("entornos", entornoServicio.listarEntornosTrabajo());
        return "index";
    }

    @GetMapping("/entornosTrabajo/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        try {
            EntornoTrabajo entorno = entornoServicio.obtenerEntornoPorId(id);
            model.addAttribute("entorno", entorno);
            return "editar-entorno";
        } catch (Exception e) {
            return "redirect:/?error=entorno_no_encontrado";
        }
    }

    @PostMapping("/entornosTrabajo/crear")
    public String crearEntornoTrabajo(@RequestParam String nombre, @RequestParam(required = false) String descripcion) {
        Empleador empleador = obtenerEmpleadorActual();
        try {
            entornoServicio.crearEntornoTrabajo(nombre, descripcion, empleador);
            return "redirect:/?success=entorno_creado";
        } catch (Exception e){
            e.printStackTrace();
            return "redirect:/?error=error_crear";
        }
    }

    @PostMapping("/entornosTrabajo/eliminar/{id}")
    public String eliminarEntorno(@PathVariable("id") Long id) {
        try {
            entornoServicio.eliminarEntorno(id);
            return "redirect:/?success=entorno_eliminado";
        } catch (Exception e) {
            return "redirect:/?error=error_eliminar";
        }
    }

    @PostMapping("/entornosTrabajo/editar/{id}")
    public String actualizarEntorno(@PathVariable("id") Long id, @RequestParam String nombre, @RequestParam(required = false) String descripcion) {
        try {
            entornoServicio.actualizarEntorno(id, nombre, descripcion);
            return "redirect:/?success=entorno_actualizado";
        } catch (Exception e) {
            return "redirect:/entornosTrabajo/editar/" + id + "?error=error_actualizar";
        }
    }

    private Empleador obtenerEmpleadorActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Empleador empleador = empleadorServicio.getEmail(username);
        if (empleador == null) {
            empleador = empleadorServicio.getUsername(username);
        }
        return empleador;
    }

}