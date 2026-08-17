package org.example.backend.repository;

import org.example.backend.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    @Query("SELECT t FROM Tarea t WHERE t.empleado_id.id = :empleadoId ORDER BY t.createdAt DESC ")
    List<Tarea> findTareaByIdEmpleadoOrdenada(@Param("empleadoId") Long id);


    @Query("SELECT t FROM Tarea t WHERE t.empleado_id.id = :empleadoId ORDER BY t.prioridad DESC, t.fechaVencimiento ASC ")
    List<Tarea> findTareaByEmpleado_idOrderByPrioridadAscFechaVencimientoDesc(@Param("empleadoId") Long id);
}
