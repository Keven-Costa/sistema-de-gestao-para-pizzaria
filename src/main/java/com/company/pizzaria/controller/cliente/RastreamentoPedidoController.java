package com.company.pizzaria.controller.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller                                       
@RequestMapping("/rastreamento-pedido")
public class RastreamentoPedidoController {

    @GetMapping("/{id}")
    public String mostrarPaginaRastreamento(@PathVariable Long id, Model model) {
        model.addAttribute("pedidoId", id);
        return "rastreamento-pedido";            
    }
}
