package com.company.pizzaria.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pizzaria.model.entity.Pizza;
import com.company.pizzaria.repository.PizzaRepository;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	public List<Pizza> listarTodas() {
		return pizzaRepository.findAll();
	}

	public Map<String, List<Pizza>> listarPizzasAgrupadasPorTipo() {
		List<Pizza> pizzas = listarTodas();
		Map<String, List<Pizza>> pizzasPorTipo = new HashMap<>();

		for (Pizza pizza : pizzas) {
			String tipo = pizza.getTipo();
			pizzasPorTipo.computeIfAbsent(tipo, k -> new ArrayList<>()).add(pizza);
		}

		return pizzasPorTipo;
	}

	public Pizza getPizzaById(Long id) {
		return pizzaRepository.findById(id).orElse(null);
	}

	public Optional<Pizza> buscarPorId(Long id) {
		return pizzaRepository.findById(id);
	}

	public void salvar(Pizza pizza) {
		pizzaRepository.save(pizza);
	}

	public void atualizar(Pizza pizza) {
		if (pizzaRepository.existsById(pizza.getId())) {
			pizzaRepository.save(pizza);
		} else {
			throw new RuntimeException("Pizza com ID " + pizza.getId() + " não encontrada para atualização.");
		}
	}

	public void excluir(Long id) {
		if (pizzaRepository.existsById(id)) {
			pizzaRepository.deleteById(id);
		} else {
			throw new RuntimeException("Pizza com ID " + id + " não encontrada para exclusão.");
		}
	}

}
