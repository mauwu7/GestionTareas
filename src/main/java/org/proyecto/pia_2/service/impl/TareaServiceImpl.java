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
import java.util.Iterator;
import java.util.List;

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
            Empleado empleado = empleadoRepository.findByUsername(nombreEmpleado).orElseThrow();

            tarea.setFechaCreacion(LocalDate.now());

            empleado.getTareasAsignadas().add(tarea);
            tarea.setEmpleado(empleado);

            tareaRepository.save(tarea);
            empleadoRepository.save(empleado);
            return tarea;
        }
        else{
            throw new UsuarioNotFoundException("No se encuentra ningun usuario registrado con el nombre: " + nombreEmpleado);
        }
    }
    @Override
    public void EditarPrioridadTarea(Long id, Integer prioridad, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {

        if(empleadoRepository.existsByUsername(nombreEmpleado)){
            boolean flag = true;
            Empleado empleado = empleadoRepository.findByUsername(nombreEmpleado).orElseThrow();
            List<TareaIndividual> tareas = empleado.getTareasAsignadas();
            for(TareaIndividual tarea : tareas){
                if(tarea.getTarea_id().equals(id)){
                    tarea.setPrioridad(prioridad);
                    flag=false;
                    break;
                }
            }
            if(flag){
                throw new TareaNotFoundException("No se encuentra el tarea con el id " + id);
            }
            else{
                empleado.setTareasAsignadas(tareas);
                empleadoRepository.save(empleado);
            }
        }
        else{
            throw new UsuarioNotFoundException("No se encontro ningun usario con el nombre: " + nombreEmpleado);
        }
    }

    @Override
    public void EditarFechaVencimiento(Long id, LocalDate fechaVencimiento, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {
        if(empleadoRepository.existsByUsername(nombreEmpleado)){
            boolean flag = true;
            Empleado empleado = empleadoRepository.findByUsername(nombreEmpleado).orElseThrow();
            List<TareaIndividual> tareas = empleado.getTareasAsignadas();

            for(TareaIndividual tarea : tareas){
                if(tarea.getTarea_id().equals(id)){
                    tarea.setFechaVencimiento(fechaVencimiento);
                    flag=false;
                    break;
                }
            }

            if(flag){
                throw new TareaNotFoundException("No se tiene registro de ninguna tarea con id: "+id);
            }
            else{
                empleado.setTareasAsignadas(tareas);
                empleadoRepository.save(empleado);
            }

        }
        else{
            throw new UsuarioNotFoundException("No se tiene registro de ningun usuario con nombre: "+nombreEmpleado);
        }
    }

    @Override
    public void FinalizarTarea(Long id, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {
        if(empleadoRepository.existsByUsername(nombreEmpleado)){
            boolean flag = true;
            Empleado empleado =  empleadoRepository.findByUsername(nombreEmpleado).orElseThrow();
            List<TareaIndividual>  tareas = empleado.getTareasAsignadas();
            Iterator<TareaIndividual> iterator = tareas.iterator();
            while(iterator.hasNext()){
                TareaIndividual tarea = iterator.next();
                if(tarea.getTarea_id().equals(id)){
                    iterator.remove();
                    flag=false;
                    break;
                }
            }
            if(flag){
                throw new TareaNotFoundException("No se tiene registro de la tarea con id: "+id);
            }
            else{
                empleado.setTareasAsignadas(tareas);
                empleadoRepository.save(empleado);
            }
        }
        else{
            throw new UsuarioNotFoundException("No se tiene registro de ningun usario con nombre: "+nombreEmpleado);
        }
    }
}
