package org.proyecto.pia_2.service.impl;
import jakarta.transaction.Transactional;
import org.proyecto.pia_2.DTO.TareaDTO;
import org.proyecto.pia_2.exception.*;
import org.proyecto.pia_2.model.*;
import org.proyecto.pia_2.repository.*;
import org.proyecto.pia_2.service.EmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.List;

@Service
public class EmpleadorServiceimpl implements EmpleadorService {

    TareaRepository tareaRepository;
    private final EmpleadorRepository empleadorRepository;
    EmpleadoRepository empleadoRepository;
    private final EntornoTrabajoServiceImpl entornoTrabajoService;
    private final EquipoServiceImpl equipoService;
    private final TareaServiceImpl tareaService;

    @Autowired
    public EmpleadorServiceimpl(EmpleadorRepository empleadorRepository,EquipoServiceImpl equipoService, TareaRepository tareaRepository, EmpleadoRepository empleadoRepository, EntornoTrabajoServiceImpl entornoTrabajoService, TareaServiceImpl tareaService) {
        this.empleadorRepository = empleadorRepository;
        this.tareaRepository = tareaRepository;
        this.empleadoRepository = empleadoRepository;
        this.entornoTrabajoService = entornoTrabajoService;
        this.equipoService = equipoService;
        this.tareaService = tareaService;
    }

    @Override
    public Empleador agregarEmpleador(Empleador empleador) throws UsuarioRegistradoException {
        if(empleadorRepository.existsByEmail(empleador.getEmail())){
            throw new UsuarioRegistradoException("El email ya se encuentra registrado");
        }
        else if(empleadorRepository.existsByUsername(empleador.getUsername())){
            throw new UsuarioRegistradoException("El nombre de usaurio ya se encuentra registrado");
        }
        else{
            return empleadorRepository.save(empleador);
        }
    }

    //Este metodo puede eliminarse despues
    @Override
    public Empleador GetEmpleador(Long id) throws UsuarioNotFoundException {
        return empleadorRepository.findById(id).orElseThrow(()-> new UsuarioNotFoundException("No existe el empleador con el id: " + id));
    }

    @Override
    public Empleador EditarEmpleador(Empleador empleador, Long id) throws UsuarioNotFoundException, UsuarioRegistradoException {
        Empleador  empleadorEditado = empleadorRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("El empleador con el id: " + id + "no existe"));

        if(empleadorRepository.existsByUsername(empleador.getUsername())){
            throw new UsuarioRegistradoException("El nombre de usaurio ya se encuentra registrado");
        }
        else if(empleadorRepository.existsByEmail(empleador.getEmail())){
            throw  new UsuarioRegistradoException("El email ya se encuentra registrado");
        }
        else{
            empleadorEditado.setUsername(empleador.getUsername());
            empleadorEditado.setEmail(empleador.getEmail());
            empleadorEditado.setPassword(empleador.getPassword());
            empleadorRepository.save(empleadorEditado);
            return empleadorEditado;
        }
    }

    @Override
    public List<Empleador> obtenerEmpleadores() {
        return empleadorRepository.findAll();
    }

    @Override
    public void EliminarEmpleador(Long id) throws UsuarioNotFoundException {
        if(empleadorRepository.existsById(id)){
            empleadorRepository.deleteById(id);
        }
        else{
            throw new UsuarioNotFoundException("No existe el empleador con el id: " + id);
        }
    }

    //------------------------------------------------------
    @Override
    @Transactional
    public Empleador agregarEntornoTrabajo(EntornoTrabajo entornoTrabajo, Long id) throws UsuarioNotFoundException, EquipoRegistradoException {
        Empleador empleadorEditado = empleadorRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("No existe el empleado con el id: " + id));
        return  empleadorRepository.save(entornoTrabajoService.CrearEntornoTrabajo(entornoTrabajo, empleadorEditado));
    }

    @Override
    public List<EntornoTrabajo> obtenerEntornoTrabajos(Long id) throws  UsuarioNotFoundException{ //Obtener los entornos de trabajo de un usauario
        Empleador empladorBuscado= empleadorRepository.findById(id).orElseThrow(()-> new UsuarioNotFoundException("No existe ningun usuario registrado con el id: "+ id));
        return empladorBuscado.getEntornosDeTrabajo();
    }

    @Transactional
    @Override
    public EntornoTrabajo AgregarEquipoEnEntornoDeTrabajo(Equipo equipo, String nombreEntornoDeTrabajo) throws EquipoNotFoundException,EquipoRegistradoException {
        return entornoTrabajoService.AgregarEquipo(equipo, nombreEntornoDeTrabajo);
    }

    @Override
    public EntornoTrabajo EditarEntorno(EntornoTrabajo entornoTrabajoEditado,String nombreEntornoDeTrabajo) throws EquipoNotFoundException, EquipoRegistradoException {
      return entornoTrabajoService.EditarEntornoDeTrabajo(entornoTrabajoEditado, nombreEntornoDeTrabajo);
    }

    @Override
    @Transactional
    public void EliminarEntornoDeTrabajo(String nombre) throws EquipoNotFoundException {
        entornoTrabajoService.EliminarEntornoDeTrabajo(nombre);
    }
    //------------------------------------------------//


    @Override
    public Equipo AgregarEmpleadoaEquipo(Empleado empleado, String nombreDeEquipo) throws EquipoNotFoundException, UsuarioRegistradoException {
        return equipoService.AgregarEmpleado(empleado, nombreDeEquipo);
    }

    @Override
    public Equipo EditarEquipo(Equipo equipoEditado, String nombreEquipo) throws EquipoRegistradoException, EquipoNotFoundException {
        return equipoService.EditarEquipo(equipoEditado, nombreEquipo);
    }

    @Override
    @Transactional
    public void eliminarEquipo(String nombreDeEquipo) throws EquipoNotFoundException {
        equipoService.EliminarEquipo(nombreDeEquipo);
    }

    //--------------------------------Operaciones en las tareas----------------------------------//

    @Override
    public TareaIndividual AgregarTarea(TareaIndividual tareaIndividual, String nombreEmpleado) throws UsuarioNotFoundException {
        return tareaService.AgregarTarea(tareaIndividual, nombreEmpleado);
    }

}
