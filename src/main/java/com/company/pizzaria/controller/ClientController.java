package com.company.pizzaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    
    @GetMapping("/")
    public String mostrarHome() {
        return "forward:/index.html"; // Redireciona para o arquivo estático
    }
    
    @GetMapping("/checkout")
    public String mostrarCheckout() {
        return "forward:/checkout.html"; // Redireciona para o arquivo estático
    }
    
    @GetMapping("/confirmacao-pedido")
    public String mostrarConfirmacaoPedido() {
        return "forward:/confirmacao-pedido.html"; // Redireciona para o arquivo estático
    }
    
    @GetMapping("/montagem-pedido")
    public String mostrarMontagemPedido() {
        return "forward:/montagem-pedido.html"; // Redireciona para o arquivo estático
    }
    
    @GetMapping("/rastreamento-pedido")
    public String mostrarRastreamentoPedido() {
        return "forward:/rastreamento-pedido.html"; // Redireciona para o arquivo estático
    }
    
}