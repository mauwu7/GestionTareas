package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "grupo")
public class Grupo {

    public Grupo() {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreGrupo;


    private String descripcion;

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Empleado> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Empleado> miembros) {
        this.miembros = miembros;
    }

    public Empleador getId_empleador() {
        return id_empleador;
    }

    public void setId_empleador(Empleador id_empleador) {
        this.id_empleador = id_empleador;
    }

    @OneToMany(mappedBy = "id_grupo", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Empleado> miembros;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Empleador id_empleador;
}
