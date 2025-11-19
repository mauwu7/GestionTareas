package org.proyecto.pia_2.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipo_id;

    @NotEmpty
    private String nombreEquipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private EntornoTrabajo entornoTrabajo;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Empleado> empleados = new ArrayList<>();

    /*
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TareaIndividual> tareas = new ArrayList<>();

     */
    public Equipo(String nombreEquipo) {setNombreEquipo(nombreEquipo);}

    public Equipo() {}

    public Long getEquipo_id() {
        return equipo_id;
    }

    public void setEquipo_id(Long equipo_id) {
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
