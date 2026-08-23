package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Empleado extends Usuario {

    public Empleado() {
    }

    public Empleado(String email, String username, String passwordUser, RolNombre rolNombre, String name){
        super(email, username, passwordUser, rolNombre, name);
    }

    @OneToMany(mappedBy = "empleado_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tarea> tareas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Grupo id_grupo;

    public Grupo getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Grupo id_grupo) {
        this.id_grupo = id_grupo;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
}
