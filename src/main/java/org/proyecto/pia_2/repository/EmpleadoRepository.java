package org.proyecto.pia_2.repository;
import org.proyecto.pia_2.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Boolean existsByUsername(String username);
    Empleado findEmpleadosByUsername(String username);
    Boolean existsByEmail(String email);
}
