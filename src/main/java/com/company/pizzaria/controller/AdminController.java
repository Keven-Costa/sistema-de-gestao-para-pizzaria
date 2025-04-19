package com.company.pizzaria.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping({"/login", "/"})
    public String loginPage() {
        return "admin/login";
    }
    
    @GetMapping("/gestao-ingredientes")
    public String gestaoIngredientesPage() {
        return "admin/gestao-ingredientes";
    }
    
    @GetMapping("/gestao-pedidos")
    public String gestaoPedidoPage() {
        return "admin/gestao-pedidos";
    }
    
    @GetMapping("/painel-promocoes")  // Alterado para coincidir com o HTML
    public String painelPromocoes() {
        return "admin/painel-promocoes";
    }
    // Adicione também para Configurações e Sair se necessário
    @GetMapping("/configuracoes")
    public String configuracoes() {
        return "admin/test";
    }
}