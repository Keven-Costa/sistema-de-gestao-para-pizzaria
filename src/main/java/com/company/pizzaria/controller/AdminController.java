package com.company.pizzaria.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/dashboard")
    public String exibirDashboard() {
        return "admin/dashboard";
    }

    @GetMapping({"/login", "/"})
    public String exibirLogin() {
        return "admin/login";
    }
    
//    @GetMapping("/gestao-pedidos")
//    public String exibirGestaoPedido() {
//        return "admin/gestao-pedidos";
//    }
    
    @GetMapping("/form-ingrediente")
    public String exibirFormIngrediente() {
        return "admin/form-ingrediente";
    }
}