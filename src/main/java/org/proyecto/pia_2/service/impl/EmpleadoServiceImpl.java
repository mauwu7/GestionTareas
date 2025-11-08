package org.proyecto.pia_2.service.impl;
import jakarta.transaction.Transactional;
import org.proyecto.pia_2.exception.TareaNotFoundException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.TareaIndividual;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.proyecto.pia_2.service.EmpleadoService;
import org.proyecto.pia_2.service.PlanificadorTareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.List;

//Revisar despues en los otros metodos que las listas si tengan una implementacion
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    @Transactional
    public List<TareaIndividual> ConsultarTareas(Long idEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {
        Empleado empledoConsultado = empleadoRepository.findById(idEmpleado).orElseThrow(() -> new UsuarioNotFoundException("No se encontro ningun usuario con id: "+idEmpleado));
        if(empledoConsultado.getTareasAsignadas().isEmpty()){
            throw new TareaNotFoundException("El usuario no tiene niguna tareas asignada");
        }
        else{
            List<TareaIndividual> tareas = empledoConsultado.getTareasAsignadas();
            tareas.sort(new PlanificadorTareas());
            return tareas;
        }
    }

    @Override
    public Empleado ConsultarInformacion(Long idEmpleado) throws UsuarioNotFoundException {
        return empleadoRepository.findById(idEmpleado).orElseThrow(() -> new UsuarioNotFoundException("No se encontro ningun usuario"));
    }

    @Override //En este y otros metodos podria eliminarse la tarea directamente con el id y un metodo del repositorio TareaIndividual
    @Transactional
    public void completarTarea(Long idEmpleado, Long idTarea) throws UsuarioNotFoundException, TareaNotFoundException {
        Empleado empleado = empleadoRepository.findById(idEmpleado).orElseThrow(() -> new UsuarioNotFoundException("No se encontro ningun usuario"));
        if(empleado.getTareasAsignadas().isEmpty()){
            throw new TareaNotFoundException("El usuario no tiene niguna tareas asignada");
        }
        else{
            boolean flag = true;
            List<TareaIndividual> tareas = empleado.getTareasAsignadas();
            Iterator<TareaIndividual> it = tareas.iterator();
            TareaIndividual tareaCompletada = new TareaIndividual();
            while(it.hasNext()){
                TareaIndividual tarea = it.next();
                if(tarea.getTarea_id().equals(idTarea)){
                    tareaCompletada = tarea;
                    it.remove();
                    flag = false;
                }
            }
            if(flag){
                throw new TareaNotFoundException("No se encontro niguna tare con id: "+idTarea);
            }
            else{
                empleado.setTareasAsignadas(tareas);
                empleadoRepository.save(empleado);
            }
        }
    }
}

