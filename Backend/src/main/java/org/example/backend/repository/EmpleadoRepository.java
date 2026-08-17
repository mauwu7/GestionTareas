package org.example.backend.repository;

import org.example.backend.model.Empleado;
import org.example.backend.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Boolean existsByEmail(String email);

}
