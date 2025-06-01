package com.company.pizzaria.controller.cliente;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.company.pizzaria.model.entity.Pizza;
import com.company.pizzaria.service.PizzaService;
import org.springframework.ui.Model;

@Controller("index")
public class HomeController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/")
	public String exibirPaginaHome(Model model) {
		Map<String, List<Pizza>> pizzasAgrupadas = pizzaService.listarPizzasAgrupadasPorTipo();
		model.addAttribute("pizzasPorTipo", pizzasAgrupadas);
		return "index";
	}
}
