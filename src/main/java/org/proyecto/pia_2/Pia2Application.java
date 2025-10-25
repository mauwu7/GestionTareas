package org.proyecto.pia_2;

import org.proyecto.pia_2.model.Tarea;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Queue;

@SpringBootApplication
public class Pia2Application {

    public static void main(String[] args) {
        SpringApplication.run(Pia2Application.class, args);
    }

    public static class GestorTareasUsuario {
        Queue<Tarea> tareasPendientes;
    }
}
