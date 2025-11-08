package org.proyecto.pia_2.service.impl;
import jakarta.transaction.Transactional;
import org.proyecto.pia_2.exception.EquipoNotFoundException;
import org.proyecto.pia_2.exception.EquipoRegistradoException;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.model.Equipo;
import org.proyecto.pia_2.repository.EntornoTrabajoRepository;
import org.proyecto.pia_2.repository.EquipoRepository;
import org.proyecto.pia_2.service.EntornoTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntornoTrabajoServiceImpl implements EntornoTrabajoService {

    EntornoTrabajoRepository entornoTrabajoRepository;
    EquipoRepository equipoRepository;

    @Autowired
    public EntornoTrabajoServiceImpl(EntornoTrabajoRepository entornoTrabajoRepository, EquipoRepository equipoRepository) {
        this.entornoTrabajoRepository = entornoTrabajoRepository;
        this.equipoRepository = equipoRepository;
    }

    @Override
    public List<Equipo> ConsultarEquipos(Long idEntorno) throws EquipoNotFoundException {
        EntornoTrabajo entornoTrabajo = entornoTrabajoRepository.findById(idEntorno).orElseThrow(()->new EquipoNotFoundException("No se tiene registro de ningun equipo con ese id"));
        return entornoTrabajo.getEquiposEntornos();
    }

    @Override
    public Empleador CrearEntornoTrabajo(EntornoTrabajo entornoTrabajo, Empleador empleador) throws EquipoRegistradoException {
        if(entornoTrabajoRepository.existsByNombre(entornoTrabajo.getNombre())){
            throw new EquipoRegistradoException("El entorno de trabajo ya se encuentra registrado ");
        }
        else{
            empleador.getEntornosDeTrabajo().add(entornoTrabajo);
            entornoTrabajo.setEmpleador(empleador);
            return empleador;
        }
    }

    @Override
    public EntornoTrabajo AgregarEquipo(Equipo equipo, String nombreEntornoDeTrabajo) throws EquipoRegistradoException, EquipoNotFoundException {
        if(entornoTrabajoRepository.existsByNombre(nombreEntornoDeTrabajo)){
            if(equipoRepository.existsByNombreEquipo(equipo.getNombreEquipo())){
                throw new EquipoRegistradoException("El equipo ya se encuentra registrado ");
            }
            else{
                EntornoTrabajo entornoTrabajo = entornoTrabajoRepository.findByNombre(nombreEntornoDeTrabajo);
                entornoTrabajo.getEquiposEntornos().add(equipo);
                equipo.setEntornoTrabajo(entornoTrabajo);
                return entornoTrabajoRepository.save(entornoTrabajo);
            }
        }
        else{
            throw new EquipoNotFoundException("No se tiene registro de ningun equipo con id: "+nombreEntornoDeTrabajo);
        }
    }

    @Override
    public EntornoTrabajo EditarEntornoDeTrabajo(EntornoTrabajo entornoTrabajo, String nombreEntornoDeTrabajo) throws EquipoRegistradoException, EquipoNotFoundException {
        if(entornoTrabajoRepository.existsByNombre(nombreEntornoDeTrabajo)){
            if(entornoTrabajoRepository.existsByNombre(entornoTrabajo.getNombre())){
                throw new EquipoRegistradoException("Ya se encuentra un equipo registrado con ese nombre");
            }
            else{
                EntornoTrabajo entornoTrabajoEditado = entornoTrabajoRepository.findByNombre(nombreEntornoDeTrabajo);
                entornoTrabajoEditado.setNombre(entornoTrabajo.getNombre());
                return entornoTrabajoRepository.save(entornoTrabajoEditado);
            }
        }
        else{
            throw new EquipoNotFoundException("No se encontro ningun equipo registrado con ese nombre");
        }
    }

    @Override
    public void EliminarEntornoDeTrabajo(String nombreEntornoDeTrabajo) throws EquipoNotFoundException {
        if(entornoTrabajoRepository.existsByNombre(nombreEntornoDeTrabajo)){
           entornoTrabajoRepository.deleteByNombre(nombreEntornoDeTrabajo);
        }
        else{
            throw new EquipoNotFoundException("No se tiene registro de ningun equipo con ese nombre");
        }
    }

}
