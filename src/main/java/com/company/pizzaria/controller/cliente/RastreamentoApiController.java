package com.company.pizzaria.controller.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.pizzaria.dto.RastreamentoPedidoDTO;
import com.company.pizzaria.service.PedidoService;

@RestController
@RequestMapping("/rastreamento-pedido")
public class RastreamentoApiController {

	private final PedidoService pedidoService;

	public RastreamentoApiController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping("/{id}/rastreamento")
	public ResponseEntity<RastreamentoPedidoDTO> rastrearPedido(@PathVariable Long id) {
		RastreamentoPedidoDTO dto = pedidoService.getRastreamentoPedido(id);
		return ResponseEntity.ok(dto);
	}
}
