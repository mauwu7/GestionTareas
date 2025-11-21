package org.proyecto.pia_2.repository;
import org.proyecto.pia_2.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByEquipoIsNull();
    Optional<Empleado> findByUsername(String username);

    boolean existsByUsername(String nombreEmpleado);
}
