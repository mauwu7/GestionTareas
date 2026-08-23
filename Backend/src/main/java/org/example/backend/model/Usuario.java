package org.example.backend.model;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolNombre rol;

    public RolNombre getRol() {
        return rol;
    }

    public void setRol(RolNombre rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordUser='" + passwordUser + '\'' +
                '}';
    }

    public Usuario(){}

    public Usuario(String email, String username, String passwordUser, RolNombre rolNombre, String name){
        this.rol = rolNombre;
        this.name = name;
        this.passwordUser = passwordUser;
        this.username = username;
        this.email = email;
    }
}
