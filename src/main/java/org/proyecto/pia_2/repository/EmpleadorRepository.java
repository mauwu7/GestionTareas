package org.proyecto.pia_2.repository;
import org.proyecto.pia_2.model.Empleador;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadorRepository extends JpaRepository<Empleador,Long> {

    Boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Empleador findByUsername(String username);
}
