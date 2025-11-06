package org.proyecto.pia_2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.NaturalId;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipo_id;

    @NotEmpty
    private String nombreEquipo;

    @ManyToOne(fetch = FetchType.LAZY)
    private EntornoTrabajo entornoTrabajo;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empleado> empleados = new ArrayList<>();

    /*
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TareaIndividual> tareas = new ArrayList<>();

     */
    public Equipo() {}

    public Integer getEquipo_id() {
        return equipo_id;
    }

    public void setEquipo_id(Integer equipo_id) {
        this.equipo_id = equipo_id;
    }

    /*
    public List<TareaIndividual> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaIndividual> tareas) {
        this.tareas = tareas;
    }
    */
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public EntornoTrabajo getEntornoTrabajo() {
        return entornoTrabajo;
    }

    public void setEntornoTrabajo(EntornoTrabajo entornoTrabajo) {
        this.entornoTrabajo = entornoTrabajo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

}
