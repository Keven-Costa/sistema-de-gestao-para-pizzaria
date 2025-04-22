package com.company.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.pizzaria.model.entity.Promocao;
import com.company.pizzaria.repository.PromocaoRepository;

@Controller
@RequestMapping("/admin/painel-promocoes")
public class PromocaoController {
    
    @Autowired
    private PromocaoRepository promocaoRepository;
    
    @GetMapping
    public String exibirPainelPromocoes(Model model) {
        model.addAttribute("promocao", new Promocao()); 
        model.addAttribute("promocoes", promocaoRepository.findAll()); 
        return "admin/painel-promocoes";
    }
    
    @PostMapping("/salvar")
    public String salvarPromocao(@ModelAttribute Promocao promocao) {
        promocaoRepository.save(promocao);
        return "redirect:/admin/painel-promocoes"; 
    }
    
	@GetMapping("/invalidar/{id}")
	public String invalidarPromocao(@PathVariable Long id) {
		try {
			promocaoRepository.deleteById(id);
			return "redirect:/admin/painel-promocoes?sucesso=promocao excluído com sucesso";
		} catch (Exception e) {
			return "redirect:/admin/painel-promocoes?erro=Erro ao excluir promocao";
		}
	}
}