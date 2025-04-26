package com.company.pizzaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    
    @GetMapping("/")
    public String exibirHome() {
        return "forward:/index.html"; 
    }
    
    @GetMapping("/checkout")
    public String exibirCheckout() {
        return "forward:/checkout.html"; 
    }
    
    @GetMapping("/confirmacao-pedido")
    public String exibirConfirmacaoPedidos() {
        return "forward:/confirmacao-pedido.html"; 
    }
    
    @GetMapping("/montagem-pedidos")
    public String exibirMostrarMontagemPedido() {
        return "forward:/montagem-pedidos.html"; 
    }
    
    @GetMapping("/rastreamento-pedido")
    public String exibirRastreamentoPedido() {
        return "forward:/rastreamento-pedido.html"; 
    }
    
}