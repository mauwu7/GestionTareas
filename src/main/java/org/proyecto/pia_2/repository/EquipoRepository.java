package org.proyecto.pia_2.repository;
import org.proyecto.pia_2.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    boolean existsByNombreEquipo(String nombreEquipo);
    Equipo  findByNombreEquipo(String nombreEquipo);
    void deleteByNombreEquipo(String nombreEquipo);
}
