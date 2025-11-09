package org.proyecto.pia_2.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "empleado")
public class Empleado extends  Usuario{

    private boolean privilegiosAdministrador;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empleado_id;*/

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TareaIndividual> tareasAsignadas = new ArrayList<>();

    @ElementCollection
    private List<String> notificaciones = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo equipo;

    //Manejar por separado el modo en que se ingresaran los equipos. Por otra parte, mantener el constructor en la clase abstracta
    public Empleado(String username, String email, String password) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public Empleado() {}

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    public Equipo getEquipo() {
        return equipo;
    }

    /*public void setEmpleado_id(Long empleado_id) {this.empleado_id = empleado_id;}
    public Long getEquipo_id() {return this.empleado_id;}*/

    public List<String> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<String> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public boolean isPrivilegiosAdministrador() {
        return privilegiosAdministrador;
    }

    public void setPrivilegiosAdministrador(boolean privilegiosAdministrador) {
        this.privilegiosAdministrador = privilegiosAdministrador;
    }

    public List<TareaIndividual> getTareasAsignadas() {
        return tareasAsignadas;
    }

    public void setTareasAsignadas(List<TareaIndividual> tareasAsignadas) {
        this.tareasAsignadas = tareasAsignadas;
    }

}
