package org.proyecto.pia_2.service.impl;
import org.proyecto.pia_2.exception.EmpleadorNotFoundException;
import org.proyecto.pia_2.exception.EntornoTrabajoException;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.proyecto.pia_2.repository.EmpleadorRepository;
import org.proyecto.pia_2.service.EmpleadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadorServiceimpl implements EmpleadorService {

    EmpleadorRepository empleadorRepository;

    @Autowired
    public EmpleadorServiceimpl(EmpleadorRepository empleadorRepository) {
        this.empleadorRepository = empleadorRepository;
    }

    //Añadir validaciones
    @Override
    public Empleador agregarEntornoTrabajo(EntornoTrabajo entornoTrabajo, Long id) throws EntornoTrabajoException {
        Empleador empleadorEditado = empleadorRepository.findById(id).orElseThrow();
        empleadorEditado.getEntornosDeTrabajo().add(entornoTrabajo);
        empleadorRepository.save(empleadorEditado);
        return empleadorEditado;
    }

    //Añadir validaciones
    @Override
    public void agregarEmpleador(Empleador empleador) {
        List<Empleador> empleadores = empleadorRepository.findAll();

        for (Empleador empleador1 : empleadores) {
            if(empleador1.getEmail().equals(empleador.getEmail())){
                //No se pueden registrar dos empleadores con el mismo email
            }
            else if(empleador1.getPassword().equals(empleador.getPassword())){
                //Tampoco con la misma contraseña
            }
        }

        empleadorRepository.save(empleador);
    }


    @Override
    public Empleador GetEmpleador(Long id) {
        return null;
    }

    //Añadir validaciones
    @Override
    public Empleador EditarEmpleador(Empleador empleador, Long id) throws EmpleadorNotFoundException {
        Empleador  empleadorEditado = empleadorRepository.findById(id).orElseThrow();
        empleadorEditado.setUsername(empleador.getUsername());
        empleadorEditado.setEmail(empleador.getEmail());
        empleadorEditado.setPassword(empleador.getPassword());
        empleadorRepository.save(empleadorEditado);
        return empleadorEditado;
    }

    @Override
    public List<Empleador> obtenerEmpleadores() {
        return empleadorRepository.findAll();
    }
}
