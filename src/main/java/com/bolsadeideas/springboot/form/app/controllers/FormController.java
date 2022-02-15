package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app")
public class FormController {

    //Mostrar el fomrulario en pantalla
    @GetMapping("/form")
    public String viewForm(Model model) {

        model.addAttribute("title", "New form");
        return "form";
    }

    //Procesar los datos enviados por el usuario
    //@RequestParam para capturar los valores del fomrulario con name en sus etiquetas
    @PostMapping("/form")
    public String processForm(
            Model model, @RequestParam String username, @RequestParam String password, @RequestParam String email) {

        model.addAttribute("title", "Filled form");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("email", email);
        return "filled";
    }

}
