package com.company.pizzaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String mostrarHome() {
        return "index"; // Redireciona para o arquivo em static/
    }
}