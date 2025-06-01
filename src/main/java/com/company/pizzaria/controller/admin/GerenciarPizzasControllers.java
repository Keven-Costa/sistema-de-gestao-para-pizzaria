package com.company.pizzaria.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.pizzaria.model.entity.Pizza;
import com.company.pizzaria.service.PizzaService;

@Controller
@RequestMapping("/admin")
public class GerenciarPizzasControllers {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/gerenciar-pizzas")
	public String exibirPaginaGerenciarPizzas(Model model) {
		List<Pizza> pizzas = pizzaService.listarTodas();
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("pizza", new Pizza()); // objeto vazio para o form de adicionar
		return "admin/gerenciar-pizzas";
	}

	@PostMapping("/salvar-pizza")
	public String salvarPizza(@ModelAttribute Pizza pizza) {
		pizzaService.salvar(pizza);
		return "redirect:/admin/gerenciar-pizzas?refresh=true";
	}

	@PostMapping("/editar-pizza/{id}")
	public String editarPizza(@PathVariable Long id, @ModelAttribute Pizza pizza) {
		pizza.setId(id);
		pizzaService.atualizar(pizza);
		return "redirect:/admin/gerenciar-pizzas?refresh=true";
	}

	@PostMapping("/excluir-pizza/{id}")
	public String excluirPizza(@PathVariable Long id) {
		pizzaService.excluir(id);
		return "redirect:/admin/gerenciar-pizzas?refresh=true";
	}
}
