package org.proyecto.pia_2.controller;

import org.proyecto.pia_2.dto.EmpleadorRegistroDTO;
import org.proyecto.pia_2.service.EmpleadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class RegistroEmpleadorController {

    @Autowired
    private EmpleadorServicio empleadorServicio;

    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("empleador", new EmpleadorRegistroDTO());
        return "registro-empleador";
    }

    // METODO GET PARA PRUEBAS TEMPORALES
    @GetMapping("/registro-procesar")
    public String registrarEmpleadorGet(@ModelAttribute("empleador") EmpleadorRegistroDTO registroDTO) {
        System.out.println("METODO GET /auth/registro EJECUTADO");
        System.out.println("Email recibido: " + registroDTO.getEmail());
        System.out.println("Username recibido: " + registroDTO.getUsername());
        System.out.println("Password recibido: " + registroDTO.getPassword());

        try {
            empleadorServicio.save(registroDTO);
            System.out.println("Registro guardado exitosamente");
            return "redirect:/auth/registro?exito";
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/auth/registro?error";
        }
    }

    @PostMapping("/registro")
    public String registrarEmpleador(@ModelAttribute("empleador") EmpleadorRegistroDTO registroDTO) {
        System.out.println("METODO POST /auth/registro EJECUTADO");
        System.out.println("Email recibido: " + registroDTO.getEmail());
        System.out.println("Username recibido: " + registroDTO.getUsername());
        System.out.println("Password recibido: " + registroDTO.getPassword());

        try {
            empleadorServicio.save(registroDTO);
            System.out.println("Registro guardado exitosamente");
            return "redirect:/auth/registro?exito";
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/auth/registro?error";
        }
    }

    @PostMapping("/debug/test")
    public String testPost(@RequestParam String test, Model model) {
        System.out.println("POST FUNCIONA! Parametro recibido: " + test);
        model.addAttribute("debug", "POST funciona correctamente - Parametro: " + test);
        model.addAttribute("empleador", new EmpleadorRegistroDTO());
        return "registro-empleador";
    }
}