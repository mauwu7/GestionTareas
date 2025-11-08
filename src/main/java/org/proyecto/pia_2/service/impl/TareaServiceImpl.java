package org.proyecto.pia_2.service.impl;
import org.proyecto.pia_2.exception.TareaNotFoundException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.TareaIndividual;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.proyecto.pia_2.repository.TareaRepository;
import org.proyecto.pia_2.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public TareaServiceImpl(TareaRepository tareaRepository,  EmpleadoRepository empleadoRepository) {
        this.tareaRepository = tareaRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public TareaIndividual AgregarTarea(TareaIndividual tarea, String nombreEmpleado) throws UsuarioNotFoundException {
        if(empleadoRepository.existsByUsername(nombreEmpleado)){
            Empleado empleado = empleadoRepository.findEmpleadosByUsername(nombreEmpleado);

            empleado.getTareasAsignadas().add(tarea);
            tarea.setEmpleado(empleado);

            tareaRepository.save(tarea);
            empleadoRepository.save(empleado); //PROVSIONAL
            return tarea;
        }
        else{
            throw new UsuarioNotFoundException("No se encuentra ningun usuario registrado con el nombre: " + nombreEmpleado);
        }
    }

    @Override
    public TareaIndividual EditarPrioridadTarea(Long id, Integer prioridad, String nombreEmpleado) throws UsuarioNotFoundException {
        return null;
    }

    @Override
    public TareaIndividual EditarFechaVencimiento(Long id, LocalDate fechaVencimiento, String nombreEmpleado) throws UsuarioNotFoundException {
        return null;
    }

    @Override
    public void FinalizarTarea(Long id, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {

    }
}
