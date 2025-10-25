package org.proyecto.pia_2.repository;
import org.proyecto.pia_2.model.Empleador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadorRepository extends JpaRepository<Empleador,Long> {
    Empleador findByUsername(String username);
}
