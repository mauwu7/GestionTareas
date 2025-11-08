package org.proyecto.pia_2.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "tarea_individual")
public class TareaIndividual extends Tarea{

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Empleado empleado;

    public TareaIndividual(String descripcion, LocalDate fechaCreacion, LocalDate fechaVencimiento, Integer prioridad, String estado){
        setDescripcion(descripcion);
        setFechaVencimiento(fechaVencimiento);
        setFechaCreacion(fechaCreacion);
        setPrioridad(prioridad);
        setEstado(estado);
    }

    public TareaIndividual(){}

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
