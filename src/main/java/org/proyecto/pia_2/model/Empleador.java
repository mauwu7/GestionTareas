package org.proyecto.pia_2.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name="empleador")
public class Empleador extends Usuario{

    @OneToMany(mappedBy = "empleador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EntornoTrabajo> entornosDeTrabajo = new ArrayList<>();

    public Empleador() {}

    public Empleador(String username, String email, String password) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public List<EntornoTrabajo> getEntornosDeTrabajo() {
        return entornosDeTrabajo;
    }

    public void setEntornosDeTrabajo(List<EntornoTrabajo> entornosDeTrabajo) {
        this.entornosDeTrabajo = entornosDeTrabajo;
    }

}
