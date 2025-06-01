package com.company.pizzaria.controller.api;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.company.pizzaria.model.entity.Promocao;
import com.company.pizzaria.service.PromocaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cupom")
public class CupomController {

	private final PromocaoService service;

	public CupomController(PromocaoService service) {
		this.service = service;
	}

	@GetMapping("/{codigo}")
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
}
