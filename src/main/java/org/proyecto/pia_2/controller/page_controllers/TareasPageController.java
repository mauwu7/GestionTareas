package org.proyecto.pia_2.controller.page_controllers;

import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.Tarea;
import org.proyecto.pia_2.model.TareaIndividual;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.proyecto.pia_2.service.EmpleadorServicio;
import org.proyecto.pia_2.service.PlanificadorTareas;
import org.proyecto.pia_2.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RequestMapping("/tareas")
@Controller
public class TareasPageController {
    @Autowired
    private TareaService tareaService;
    @Autowired
    private EmpleadorServicio empleadorServicio;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @PostMapping("/asignar-tarea/{empleado-id}")
    public String asignarTarea(@PathVariable("empleado-id") Long id,
                               @RequestParam String descripcion,
                               @RequestParam String fechaVencimiento,
                               @RequestParam Integer prioridad){
        try {
            LocalDate date = LocalDate.parse(fechaVencimiento);
            LocalDate hoy = LocalDate.now();
            TareaIndividual tarea = new TareaIndividual(descripcion, hoy, date, prioridad, "PENDIENTE");

            Empleado empleado = empleadoRepository.findById(id).orElseThrow();
            tarea.setEmpleado(empleado);

            String nombreEmpleado = empleado.getUsername();
            tareaService.AgregarTarea(tarea, nombreEmpleado);

            return "redirect:/tareas/visualizar/" + id + "?success=tarea_creada";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/tareas/visualizar/" + id + "?error=error_crear_tarea";
        }
    }

    @GetMapping("/visualizar/{empleado-id}")
    public String visualizarTareas(@PathVariable("empleado-id") Long id, Model model) {
        try {
            PlanificadorTareas planificadorTareas = new PlanificadorTareas();
            Empleado empleado = empleadoRepository.findById(id).orElseThrow();
            List<TareaIndividual> tareas = empleado.getTareasAsignadas();
            tareas.sort(planificadorTareas);

            model.addAttribute("empleado", empleado);
            model.addAttribute("tareas", empleado.getTareasAsignadas());
            return "tareas";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping("/editar-prioridad/{empleado-id}/{tarea-id}")
    public String editarPrioridad(@PathVariable("empleado-id") Long empleadoId,
                                  @PathVariable("tarea-id") Long tareaId,
                                  @RequestParam Integer prioridad) {
        try {
            Empleado empleado = empleadoRepository.findById(empleadoId).orElseThrow();
            tareaService.EditarPrioridadTarea(tareaId, prioridad, empleado.getUsername());
            return "redirect:/tareas/visualizar/" + empleadoId + "?success=prioridad_actualizada";
        } catch (Exception e) {
            return "redirect:/tareas/visualizar/" + empleadoId + "?error=error_actualizar_prioridad";
        }
    }

    @PostMapping("/editar-fecha/{empleado-id}/{tarea-id}")
    public String editarFecha(@PathVariable("empleado-id") Long empleadoId,
                              @PathVariable("tarea-id") Long tareaId,
                              @RequestParam String fechaVencimiento) {
        try {
            LocalDate fecha = LocalDate.parse(fechaVencimiento);
            Empleado empleado = empleadoRepository.findById(empleadoId).orElseThrow();
            tareaService.EditarFechaVencimiento(tareaId, fecha, empleado.getUsername());
            return "redirect:/tareas/visualizar/" + empleadoId + "?success=fecha_actualizada";
        } catch (Exception e) {
            return "redirect:/tareas/visualizar/" + empleadoId + "?error=error_actualizar_fecha";
        }
    }

    @PostMapping("/finalizar/{empleado-id}/{tarea-id}")
    public String finalizarTarea(@PathVariable("empleado-id") Long empleadoId,
                                 @PathVariable("tarea-id") Long tareaId) {
        try {
            Empleado empleado = empleadoRepository.findById(empleadoId).orElseThrow();
            tareaService.FinalizarTarea(tareaId, empleado.getUsername());
            return "redirect:/tareas/visualizar/" + empleadoId + "?success=tarea_finalizada";
        } catch (Exception e) {
            return "redirect:/tareas/visualizar/" + empleadoId + "?error=error_finalizar_tarea";
        }
    }
}