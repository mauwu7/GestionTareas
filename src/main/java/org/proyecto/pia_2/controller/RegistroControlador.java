package org.proyecto.pia_2.controller;

import org.proyecto.pia_2.service.EntornoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class RegistroControlador {
    @Autowired
    private EntornoServicio entornoServicio;

    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }
}
