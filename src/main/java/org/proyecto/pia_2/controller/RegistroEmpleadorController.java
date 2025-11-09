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

    @PostMapping("/registro")
    public String registrarEmpleador(@ModelAttribute("empleador") EmpleadorRegistroDTO registroDTO) {
        try {
            empleadorServicio.save(registroDTO);
            return "redirect:/auth/registro?exito";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/auth/registro?error";
        }
    }

}