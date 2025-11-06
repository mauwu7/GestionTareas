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
    EmpleadorRepository empleadorRepository;
    EntornoTrabajoRepository entornoTrabajoRepository;
    EquipoRepository equipoRepository;
    EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadorServiceimpl(EmpleadorRepository empleadorRepository, TareaRepository tareaRepository, EquipoRepository equipoRepository, EmpleadoRepository empleadoRepository, EntornoTrabajoRepository entornoTrabajoRepository) {
        this.empleadorRepository = empleadorRepository;
        this.tareaRepository = tareaRepository;
        this.equipoRepository = equipoRepository;
        this.empleadoRepository = empleadoRepository;
        this.entornoTrabajoRepository = entornoTrabajoRepository;
    }

    @Override
    public Empleador agregarEntornoTrabajo(EntornoTrabajo entornoTrabajo, Long id) throws UsuarioNotFoundException, EquipoRegistradoException {
        Empleador empleadorEditado = empleadorRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("No existe el empleado con el id: " + id));
        if(entornoTrabajoRepository.existsByNombre(entornoTrabajo.getNombre())){
            throw new EquipoRegistradoException("El entorno de trabajo ya se encuentra registrado");
        }
        else{
            //----------Mantener esto-----------
            empleadorEditado.getEntornosDeTrabajo().add(entornoTrabajo);
            entornoTrabajo.setEmpleador(empleadorEditado);
            //------------------------------------
            empleadorRepository.save(empleadorEditado);
            return empleadorEditado;
        }
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

    //Este metodo puede eliminarse despues //A lo mejor estas clases deberian estar anotadas con @Transactional, igual despues se puede cambiar
    @Override
    @Transactional //Mantener la anotacion, puede cambiar despues
    public List<EntornoTrabajo> obtenerEntornoTrabajos(Long id) throws  UsuarioNotFoundException{ //Obtener los entornos de trabajo de un usauario
        Empleador empladorBuscado= empleadorRepository.findById(id).orElseThrow(()-> new UsuarioNotFoundException("No existe ningun usuario registrado con el id: "+ id));
        return empladorBuscado.getEntornosDeTrabajo();
    }

    @Transactional
    @Override //Ver si no genera ningun error
    public EntornoTrabajo AgregarEquipoEnEntornoDeTrabajo(Equipo equipo, String nombreEntornoDeTrabajo) throws EquipoNotFoundException,EquipoRegistradoException {
        if(entornoTrabajoRepository.existsByNombre(nombreEntornoDeTrabajo)){
            if(equipoRepository.existsByNombreEquipo(equipo.getNombreEquipo())){
                throw new EquipoRegistradoException("El equipo con nombre: "+ equipo.getNombreEquipo()+" ya se encuentra registrado");
            }
            else{
                EntornoTrabajo entornoTrabajo = entornoTrabajoRepository.findByNombre(nombreEntornoDeTrabajo);

                entornoTrabajo.getEquiposEntornos().add(equipo);
                equipo.setEntornoTrabajo(entornoTrabajo);

                entornoTrabajoRepository.save(entornoTrabajo);
                return entornoTrabajo;
            }
        }
        else{
            throw new EquipoNotFoundException("No se tiene registro de ningun entorno de trabajo con nombre: " + nombreEntornoDeTrabajo);
        }
    }

    @Override
    @Transactional
    public EntornoTrabajo EditarEntorno(EntornoTrabajo entornoTrabajoEditado,String nombreEntornoDeTrabajo) throws EquipoNotFoundException, EquipoRegistradoException {
        if(entornoTrabajoRepository.existsByNombre(nombreEntornoDeTrabajo)){
            if(entornoTrabajoRepository.existsByNombre(entornoTrabajoEditado.getNombre())){
                throw new EquipoRegistradoException("El nombre que se ingreso ya se encuentra registrado");
            }
            else{
                EntornoTrabajo entornoTrabajo = entornoTrabajoRepository.findByNombre(nombreEntornoDeTrabajo);
                entornoTrabajo.setNombre(entornoTrabajoEditado.getNombre());
                return entornoTrabajoRepository.save(entornoTrabajo);
            }
        }
        else throw new EquipoNotFoundException("No existe ningun registro de un entorno registrado con nombre: " + nombreEntornoDeTrabajo);
    }

    @Override
    public void EliminarEntornoDeTrabajo(String nombre) throws EquipoNotFoundException {
        if(entornoTrabajoRepository.existsByNombre(nombre)){
            EntornoTrabajo entornoTrabajoEliminado = entornoTrabajoRepository.findByNombre(nombre);
            entornoTrabajoRepository.delete(entornoTrabajoEliminado);
        }
        else{
            throw new EquipoNotFoundException("No se encuentra ningun entorno registrado con nombre: " + nombre);
        }
    }
    //------------------------------------------------//

    @Transactional
    @Override
    public Equipo AgregarEmpleadoaEquipo(Empleado empleado, String nombreDeEquipo) throws EquipoNotFoundException, UsuarioRegistradoException {
        if(equipoRepository.existsByNombreEquipo(nombreDeEquipo)){
            if(empleadoRepository.existsByUsername(empleado.getUsername()) || empleadoRepository.existsByEmail(empleado.getEmail())){
                throw new UsuarioRegistradoException("Debe introducirse un email o username que no este registrado");
            }
            else{
                Equipo equipo = equipoRepository.findByNombreEquipo(nombreDeEquipo);

                equipo.getEmpleados().add(empleado);
                empleado.setEquipo(equipo);

                return equipoRepository.save(equipo);
            }
        }
        else{
            throw new EquipoNotFoundException("No se encuentra ningun equipo registrado con el nombre: " + nombreDeEquipo);
        }
    }

    @Override
    public Equipo EditarEquipo(Equipo equipoEditado, String nombreEquipo) throws EquipoRegistradoException, EquipoNotFoundException {
        if (equipoRepository.existsByNombreEquipo(nombreEquipo)) {
            if (equipoRepository.existsByNombreEquipo(equipoEditado.getNombreEquipo())) {
                throw new EquipoRegistradoException("El equipo con nombre: " + equipoEditado.getNombreEquipo() + " ya se encuentra registrado");
            } else {
                Equipo nuevoEquipo = equipoRepository.findByNombreEquipo(nombreEquipo);
                nuevoEquipo.setNombreEquipo(equipoEditado.getNombreEquipo());
                return equipoRepository.save(nuevoEquipo);
            }
        } else {
            throw new EquipoNotFoundException("No hay ningun equipo registrado con el nombre: " + nombreEquipo);
        }
    }

    @Override
    public void eliminarEquipo(String nombreDeEquipo) throws EquipoNotFoundException {
        if(equipoRepository.existsByNombreEquipo(nombreDeEquipo)){
            equipoRepository.deleteByNombreEquipo(nombreDeEquipo);
        }
        else{
            throw new EquipoNotFoundException("No hay ningun eqipo registrado con el nombre: " + nombreDeEquipo);
        }
    }



    //---------------------------------------------------------------------//
    @Transactional
    @Override
    public TareaIndividual AgregarTarea(TareaIndividual tareaIndividual, String nombreEmpleado) throws UsuarioNotFoundException {
        if(empleadoRepository.existsByUsername(nombreEmpleado)){
            Empleado empleado = empleadoRepository.findEmpleadosByUsername(nombreEmpleado);

            empleado.getTareasAsignadas().add(tareaIndividual);
            tareaIndividual.setEmpleado(empleado);

            empleadoRepository.save(empleado); //PROVSIONAL
            return tareaIndividual;
        }
        else{
            throw new UsuarioNotFoundException("No se encuentra ningun usuario registrado con el nombre: " + nombreEmpleado);
        }
    }

    @Override
    @Transactional //NO SE CONTEMPLA QUE SE EDITE LA DESCRIPCION, NI LAS ETIQUETAS. TAMPOCO QUE SEA OPCIONAL EDITAR O NO ALGUN CAMPO
    public Empleado EditarTarea(TareaDTO tarea, Long id, String nombreEmpleado) throws TareaNotFoundException, UsuarioNotFoundException {
        if(empleadoRepository.existsByUsername(nombreEmpleado)){
            boolean flag = true;
            Empleado empleado = empleadoRepository.findEmpleadosByUsername(nombreEmpleado);
            List<TareaIndividual> tareasAsignadas = empleado.getTareasAsignadas();
            for(TareaIndividual tareaAsignada : tareasAsignadas){
                if(tareaAsignada.getTarea_id().equals(id)){
                    tareaAsignada.setFechaVencimiento(tarea.getFechaVencimiento());
                    tareaAsignada.setPrioridad(tarea.getPrioridad());
                    tareaAsignada.setEstado(tarea.getEstado());
                    flag = false;
                    break;
                }
            }
            if(flag){
                throw new TareaNotFoundException("El empleado "+nombreEmpleado+" no tiene ningun tarea asignada con el id: "+id);
            }
            else{
                empleado.setTareasAsignadas(tareasAsignadas);
                return empleadoRepository.save(empleado);
            }
        }
        else{
            throw new UsuarioNotFoundException("No se encuentra ningun usuario registrado con el nombre: " + nombreEmpleado);
        }
    }

    @Override
    @Transactional
    public void eliminarTarea(Long id, String nombreEmpleado) throws UsuarioNotFoundException, TareaNotFoundException {
        boolean flag = true;
        if(empleadoRepository.existsByUsername(nombreEmpleado)){
            Empleado empleado = empleadoRepository.findEmpleadosByUsername(nombreEmpleado);
            List<TareaIndividual>  tareas = empleado.getTareasAsignadas(); //A lo mejor habria que usar un iterador aqui
            Iterator<TareaIndividual> iterator = tareas.iterator();
            while(iterator.hasNext()){
                TareaIndividual tareaAsignada = iterator.next();
                if(tareaAsignada.getTarea_id().equals(id)){
                    iterator.remove();
                    flag = false;
                    break;
                }
            }
            empleado.setTareasAsignadas(tareas);
            empleadoRepository.save(empleado);
        }
        else {
            throw new UsuarioNotFoundException("No hay ningun usuario con el nombre: " + nombreEmpleado);
        }
        if(flag){
            throw new TareaNotFoundException("No hay ninguna tarea registrada con el ID: "+id);
        }
    }
}
