package com.company.pizzaria.controller.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

// Importe a sua classe Pizza e PizzaService
import com.company.pizzaria.model.entity.Pizza;
import com.company.pizzaria.service.PizzaService;

@Controller
public class MontagemPedidosController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/montagem-pedidos")
    public String paginaMontagemPedidos(@RequestParam Long id, Model model) {
        Pizza pizza = pizzaService.getPizzaById(id);  // busca pizza pelo id
        model.addAttribute("pizza", pizza);            // passa a pizza para a view
        return "montagem-pedidos";                      // nome do template html
    }
}
