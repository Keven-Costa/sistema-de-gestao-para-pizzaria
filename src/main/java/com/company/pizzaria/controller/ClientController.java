package com.company.pizzaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    
    @GetMapping("/")
    public String exibirHome() {
        return "index"; 
    }
    
    @GetMapping("/carrinho")
    public String exibirCheckout() {
        return "carrinho"; 
    }
    
    @GetMapping("/confirmacao-pedido")
    public String exibirConfirmacaoPedidos() {
        return "confirmacao-pedido"; 
    }
    
    @GetMapping("/montagem-pedidos")
    public String exibirMostrarMontagemPedido() {
        return "montagem-pedidos"; 
    }
    
    @GetMapping("/rastreamento-pedido")
    public String exibirRastreamentoPedido() {
        return "rastreamento-pedido"; 
    }
    
}