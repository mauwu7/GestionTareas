package org.proyecto.pia_2.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "entorno_trabajo")
public class EntornoTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entorno_id;

    @NotEmpty
    @Size(min = 7, max = 30)
    private String nombre;

    @NotEmpty
    @Size(min = 7, max = 30)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleador empleador;


    @OneToMany(mappedBy = "entornoTrabajo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Equipo> equiposEntornos = new ArrayList<>();

    public EntornoTrabajo() {}

    public EntornoTrabajo(String nombre, String descripcion,  Empleador empleador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.empleador = empleador;
    }

    public Long getEntorno_id() {
        return entorno_id;
    }

    public void setEntorno_id(Long entorno_id) {
        this.entorno_id = entorno_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Equipo> getEquiposEntornos() {
        return equiposEntornos;
    }

    public void setEquiposEntornos(List<Equipo> equiposEntornos) {
        this.equiposEntornos = equiposEntornos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EntornoTrabajo that)) return false;
        return Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion);
    }
}
