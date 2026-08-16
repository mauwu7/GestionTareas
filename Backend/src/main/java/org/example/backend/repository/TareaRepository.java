package org.example.backend.repository;

import org.example.backend.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
