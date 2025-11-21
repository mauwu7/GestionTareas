package org.proyecto.pia_2.controller.page_controllers;

import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.model.Equipo;
import org.proyecto.pia_2.service.EmpleadoServicio;
import org.proyecto.pia_2.service.EntornoServicio;
import org.proyecto.pia_2.service.EquipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/entornosTrabajo/equipos")
public class EmpleadoPageController {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @Autowired
    private EquipoServicio equipoServicio;

    @Autowired
    private EntornoServicio entornoServicio;

    @PostMapping("/crear-empleado/{equipoId}")
    public String crearEmpleado(@PathVariable("equipoId") Long equipoId,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String email,
                                RedirectAttributes redirectAttributes) {
        try {
            Empleado empleado = empleadoServicio.crearEmpleado(username, password, email, equipoId);

            redirectAttributes.addFlashAttribute("success", "empleado_creado");
            return "redirect:/entornosTrabajo/editar/" + empleado.getEquipo().getEntornoTrabajo().getEntorno_id();

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("username")) {
                redirectAttributes.addFlashAttribute("error", "username_duplicado");
            } else if (e.getMessage().contains("email")) {
                redirectAttributes.addFlashAttribute("error", "email_duplicado");
            } else if (e.getMessage().contains("empleado ya existe")) {
                redirectAttributes.addFlashAttribute("error", "username_duplicado");
            } else if (e.getMessage().contains("No existe el equipo")) {
                redirectAttributes.addFlashAttribute("error", "equipo_no_encontrado");
            } else {
                redirectAttributes.addFlashAttribute("error", "error_crear_empleado");
            }
            return "redirect:/entornosTrabajo/equipos/crear-empleado/" + equipoId;
        }
    }

    @GetMapping("/crear-empleado/{equipoId}")
    public String crearEmpleadoYMostrar(@PathVariable Long equipoId, Model model) {
        try {
            Equipo equipo = equipoServicio.obtenerEquipoPorId(equipoId);
            EntornoTrabajo entorno = equipo.getEntornoTrabajo();

            // Obtener empleados del equipo
            List<Empleado> empleados = empleadoServicio.listarEmpleados().stream().filter(empleado -> empleado.getEquipo() != null &&
                            empleado.getEquipo().getEquipo_id().equals(equipoId))
                    .collect(Collectors.toList());

            model.addAttribute("equipoId", equipo.getEquipo_id());
            model.addAttribute("equipoNombre", equipo.getNombreEquipo());
            model.addAttribute("entornoId", entorno.getEntorno_id());
            model.addAttribute("entornoNombre", entorno.getNombre());
            model.addAttribute("empleados", empleados);

        } catch (Exception e) {
            model.addAttribute("equipoId", equipoId);
            model.addAttribute("equipoNombre", "Equipo no encontrado");
            model.addAttribute("entornoId", 0);
            model.addAttribute("entornoNombre", "Entorno no encontrado");
            model.addAttribute("empleados", new ArrayList<>());
        }

        return "crear-empleado";
    }

    @PostMapping("/eliminar-empleado/{equipoId}/{empleadoId}")
    public String eliminarEmpleadoDelEquipo(@PathVariable("equipoId") Long equipoId,
                                            @PathVariable("empleadoId") Long empleadoId,
                                            RedirectAttributes redirectAttributes) {
        try {
            empleadoServicio.removerEmpleadoDeEquipo(empleadoId);
            redirectAttributes.addFlashAttribute("success", "empleado_eliminado");

            Equipo equipo = equipoServicio.obtenerEquipoPorId(equipoId);
            if (equipo != null && equipo.getEntornoTrabajo() != null) {
                return "redirect:/entornosTrabajo/editar/" + equipo.getEntornoTrabajo().getEntorno_id();
            } else {
                return "redirect:/?error=equipo_no_encontrado";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "error_eliminar_empleado");
            return "redirect:/?error=error_eliminar_empleado";
        }
    }
}