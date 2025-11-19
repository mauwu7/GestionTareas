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
import java.util.List;

//Revisar despues en los otros metodos que las listas si tengan una implementacion
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final TareaServiceImpl tareaService;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,  TareaServiceImpl tareaService) {
        this.empleadoRepository = empleadoRepository;
        this.tareaService = tareaService;
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

    @Override
    public void completarTarea(String nombreEmpleado, Long idTarea) throws UsuarioNotFoundException, TareaNotFoundException {
        tareaService.FinalizarTarea(idTarea, nombreEmpleado);
    }


}

