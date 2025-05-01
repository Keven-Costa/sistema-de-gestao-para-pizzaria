package com.company.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.company.pizzaria.model.entity.Ingrediente;
import com.company.pizzaria.repository.IngredienteRepository;

@Controller
@RequestMapping("/admin/novo-gestao-ingredientes")
public class IngredienteController {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@GetMapping
	public String exibirGestaoIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("ingredientes", ingredienteRepository.findAll()); 
		return "admin/novo-gestao-ingredientes";
	}

	@PostMapping("/salvar")
	public String salvarIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
		return "redirect:/admin/novo-gestao-ingredientes"; 
	}

	@PostMapping("/editar/{id}")
	public String editarIngrediente(@PathVariable Long id, @ModelAttribute Ingrediente ingredienteAtualizado,
			BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/admin/novo-gestao-ingredientes?erro";
		}

		Ingrediente ingrediente = ingredienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Ingrediente inválido: " + id));

		ingrediente.setNome(ingredienteAtualizado.getNome());
		ingrediente.setQuantidadeAtual(ingredienteAtualizado.getQuantidadeAtual());
		ingrediente.setEstoqueMinimo(ingredienteAtualizado.getEstoqueMinimo());
		ingrediente.setUnidadeMedida(ingredienteAtualizado.getUnidadeMedida());

		ingredienteRepository.save(ingrediente);

		return "redirect:/admin/novo-gestao-ingredientes?sucesso";
	}

	@GetMapping("/excluir/{id}")
	public String excluirIngrediente(@PathVariable Long id) {
		try {
			ingredienteRepository.deleteById(id);
			return "redirect:/admin/novo-gestao-ingredientes?sucesso=Ingrediente excluído com sucesso";
		} catch (Exception e) {
			return "redirect:/admin/novo-gestao-ingredientes?erro=Erro ao excluir ingrediente";
		}
	}
	

}