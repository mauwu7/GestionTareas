package org.proyecto.pia_2.service.impl;
import org.proyecto.pia_2.exception.EquipoNotFoundException;
import org.proyecto.pia_2.exception.EquipoRegistradoException;
import org.proyecto.pia_2.exception.UsuarioRegistradoException;
import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.Equipo;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.proyecto.pia_2.repository.EquipoRepository;
import org.proyecto.pia_2.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EquipoServiceImpl(EquipoRepository equipoRepository,  EmpleadoRepository empleadoRepository) {
        this.equipoRepository = equipoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> ConsultarEmpleadosEnEquipo(Long idEquipo) throws EquipoNotFoundException {
        Equipo equipo = equipoRepository.findById(idEquipo).orElseThrow(()-> new EquipoNotFoundException("No se tiene registro de ningun equipo con id: "+idEquipo));
        return equipo.getEmpleados();
    }

    @Override
    public Equipo AgregarEmpleado(Empleado empleado, String nombreEquipo) throws EquipoNotFoundException, UsuarioRegistradoException {
        if(equipoRepository.existsByNombreEquipo(nombreEquipo)){
            if(empleadoRepository.existsByUsername(empleado.getUsername()) || empleadoRepository.existsByEmail(empleado.getEmail())){
                throw new  UsuarioRegistradoException("Debe introducrise un email o username que no este registrado");
            }
            else{
                Equipo equipo = equipoRepository.findByNombreEquipo(nombreEquipo);
                equipo.getEmpleados().add(empleado);
                empleado.setEquipo(equipo);
                return equipoRepository.save(equipo);
            }
        }
        else{
            throw new EquipoNotFoundException("No se tiene registro de ningun equipo con nombre: "+nombreEquipo);
        }
    }

    @Override
    public Equipo EditarEquipo(Equipo equipo, String nombreEquipo) throws EquipoNotFoundException, EquipoRegistradoException {
        if (equipoRepository.existsByNombreEquipo(nombreEquipo)) {
            if (equipoRepository.existsByNombreEquipo(equipo.getNombreEquipo())) {
                throw new EquipoRegistradoException("El equipo con nombre: " + equipo.getNombreEquipo() + " ya se encuentra registrado");
            } else {
                Equipo nuevoEquipo = equipoRepository.findByNombreEquipo(nombreEquipo);
                nuevoEquipo.setNombreEquipo(equipo.getNombreEquipo());
                return equipoRepository.save(nuevoEquipo);
            }
        } else {
            throw new EquipoNotFoundException("No hay ningun equipo registrado con el nombre: " + nombreEquipo);
        }
    }

    @Override
    public void EliminarEquipo(String nombreEquipo) throws EquipoNotFoundException {
        if(equipoRepository.existsByNombreEquipo(nombreEquipo)){
            equipoRepository.deleteByNombreEquipo(nombreEquipo);
        }
        else{
            throw new EquipoNotFoundException("No hay ningun eqipo registrado con el nombre: " + nombreEquipo);
        }
    }


}
