package org.proyecto.pia_2.controller.page_controllers;

import org.proyecto.pia_2.service.EquipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/entornosTrabajo")
public class EquipoPageController {
    @Autowired
    EquipoServicio equipoServicio;

    @PostMapping("/crear-equipo/{id}")
    public String crearEquipo(@PathVariable("id") Long entorno_id, @RequestParam String nombre) {
        try {
            equipoServicio.crearEquipo(nombre, entorno_id);
            return "redirect:/entornosTrabajo/editar/" + entorno_id + "?success=equipo_creado";
        } catch (Exception e){
            e.printStackTrace();
            return "redirect:/entornosTrabajo/editar/" + entorno_id + "?error=error_crear_equipo";
        }
    }

    @PostMapping("/editar-equipo/{id}")
    public String editarEquipo(@PathVariable("id") Long id, @RequestParam String nombre) {
        try {
            equipoServicio.actualizarEquipo(id, nombre);
            return "redirect:/?success=equipo_actualizado";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/entornosTrabajo/editar/" + id + "?error=error_actualizar";
        }
    }

    @PostMapping("/eliminar-equipo/{entornoId}/{equipoId}")
    public String eliminarEquipo(@PathVariable("entornoId") Long entornoId, @PathVariable("equipoId") Long equipoId) {
        try {
            equipoServicio.eliminarEquipo(equipoId);
            return "redirect:/entornosTrabajo/editar/" + entornoId + "?success=equipo_eliminado";
        }
        catch (Exception e){
            e.printStackTrace();
            return "redirect:/entornosTrabajo/editar/" + entornoId + "?error=error_eliminar_equipo";
        }
    }
}
