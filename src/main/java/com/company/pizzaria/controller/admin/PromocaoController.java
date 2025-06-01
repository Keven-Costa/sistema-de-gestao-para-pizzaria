package com.company.pizzaria.controller.admin;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.company.pizzaria.model.entity.Promocao;
import com.company.pizzaria.service.PromocaoService;

import java.util.Map;

@Controller
@RequestMapping("/admin/painel-promocoes")
public class PromocaoController {

	private final PromocaoService service;

	public PromocaoController(PromocaoService service) {
		this.service = service;
	}

	@GetMapping
	public String painel(Model model) {
		model.addAttribute("promocaoNova", new Promocao());
		model.addAttribute("ativas", service.listarAtivas());
		model.addAttribute("expirando", service.listarExpirandoEmBreve());
		return "admin/painel-promocoes";
	}

	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Promocao promocao) {
		service.salvar(promocao);
		return "redirect:/admin/painel-promocoes";
	}

	@PostMapping("/invalidar/{codigo}")
	public String invalidar(@PathVariable String codigo) {
		service.invalidar(codigo);
		return "redirect:/admin/painel-promocoes";
	}

	@GetMapping("/api/cupom/{codigo}")
	@ResponseBody
	public ResponseEntity<?> validarCupom(@PathVariable String codigo) {
		Promocao promocao = service.buscarPorCodigo(codigo);
		if (promocao == null || promocao.getValidade().isBefore(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cupom inválido");
		}

		Map<String, Object> resposta = new HashMap<>();
		resposta.put("tipo", promocao.getTipo());
		resposta.put("valor", promocao.getValor());

		return ResponseEntity.ok(resposta);
	}
	
	@PostMapping("/excluir/{codigo}")
	public String excluir(@PathVariable String codigo) {
	    service.excluir(codigo);
	    return "redirect:/admin/painel-promocoes";
	}

}