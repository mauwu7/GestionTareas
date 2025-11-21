package org.proyecto.pia_2.service.impl;

import org.proyecto.pia_2.model.Empleado;
import org.proyecto.pia_2.model.Equipo;
import org.proyecto.pia_2.repository.EmpleadoRepository;
import org.proyecto.pia_2.repository.EquipoRepository;
import org.proyecto.pia_2.service.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public List<Empleado> obtenerEmpleadosSinEquipo() {
        return empleadoRepository.findByEquipoIsNull();
    }

    @Override
    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado crearEmpleado(String username, String password, String email, Long equipoId) {
        if (empleadoRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("El empleado ya existe en el sistema");
        }

        Equipo equipo = equipoRepository.findById(equipoId).orElseThrow(() -> new RuntimeException("No existe el equipo"));
        Empleado empleado = new Empleado();
        empleado.setUsername(username);
        empleado.setPassword(passwordEncoder.encode(password));
        empleado.setEmail(email);
        empleado.setEquipo(equipo);
        return empleadoRepository.save(empleado);
    }

    @Override
    public void asignarEmpleadoAEquipo(Long empleadoId, Long equipoId) {
        try {
            Empleado empleado = empleadoRepository.findById(empleadoId)
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

            Equipo equipo = equipoRepository.findById(equipoId)
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            empleado.setEquipo(equipo);
            empleadoRepository.save(empleado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removerEmpleadoDeEquipo(Long empleadoId) {
        try {
            Empleado empleado = empleadoRepository.findById(empleadoId).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            empleado.setEquipo(null);
            empleadoRepository.save(empleado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Empleado actualizarEmpleado(Long id, String username) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // Verificar si el username ya está en uso por otro empleado
        if (!empleado.getUsername().equals(username)) {
            Optional<Empleado> empleadoExistente = empleadoRepository.findByUsername(username);
            if (empleadoExistente.isPresent() && !empleadoExistente.get().getUsuario_id().equals(id)) {
                throw new RuntimeException("El nombre de usuario ya está en uso");
            }
        }

        empleado.setUsername(username);
        return empleadoRepository.save(empleado);    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
