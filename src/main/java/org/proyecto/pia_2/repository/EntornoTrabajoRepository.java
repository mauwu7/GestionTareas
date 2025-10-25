package org.proyecto.pia_2.repository;
import org.proyecto.pia_2.model.EntornoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntornoTrabajoRepository extends JpaRepository<EntornoTrabajo,Long> {
}
