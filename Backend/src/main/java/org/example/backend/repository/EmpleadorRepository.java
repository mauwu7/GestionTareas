package org.example.backend.repository;

import org.example.backend.model.Empleador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadorRepository extends JpaRepository<Empleador, Long> {
    Boolean existsByEmail(String email);
}
