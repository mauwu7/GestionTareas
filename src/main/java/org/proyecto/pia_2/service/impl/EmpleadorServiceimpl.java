package org.proyecto.pia_2.service.impl;
import jakarta.transaction.Transactional;
import org.proyecto.pia_2.exception.EquipoRegistradoException;
import org.proyecto.pia_2.exception.UsuarioNotFoundException;
import org.proyecto.pia_2.exception.UsuarioRegistradoException;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.proyecto.pia_2.repository.EntornoTrabajoRepository;
import org.proyecto.pia_2.service.EmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadorServiceimpl implements EmpleadorService {

    EmpleadorRepository empleadorRepository;
    EntornoTrabajoRepository entornoTrabajoRepository;

    @Autowired
    public EmpleadorServiceimpl(EmpleadorRepository empleadorRepository) {
        this.empleadorRepository = empleadorRepository;
    }

    @Override
    public Empleador agregarEntornoTrabajo(EntornoTrabajo entornoTrabajo, Long id) throws UsuarioNotFoundException, EquipoRegistradoException {
        Empleador empleadorEditado = empleadorRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("No existe el empleado con el id: " + id));
        if(entornoTrabajoRepository.existsByNombre(entornoTrabajo.getNombre())){
            throw new EquipoRegistradoException("El entorno de trabajo ya se encuentra registrado");
        }
        else{
            empleadorEditado.getEntornosDeTrabajo().add(entornoTrabajo);
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

    //Este metodo puede eliminarse despues
    @Override
    @Transactional //Mantener la anotacion, puede cambiar despues
    public List<EntornoTrabajo> obtenerEntornoTrabajos(Long id) throws  UsuarioNotFoundException{ //Obtener los entornos de trabajo de un usauario
        Empleador empladorBuscado= empleadorRepository.findById(id).orElseThrow(()-> new UsuarioNotFoundException("No existe ningun usuario registrado con el id: "+ id));
        return empladorBuscado.getEntornosDeTrabajo();
    }

}
