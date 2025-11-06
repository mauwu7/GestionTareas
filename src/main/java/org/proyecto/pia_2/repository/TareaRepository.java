package org.proyecto.pia_2.repository;
import org.proyecto.pia_2.model.TareaIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<TareaIndividual, Long> {
}
