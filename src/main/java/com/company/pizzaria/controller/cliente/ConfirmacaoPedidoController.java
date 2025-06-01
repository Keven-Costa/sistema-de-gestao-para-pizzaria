package com.company.pizzaria.controller.cliente;

import com.company.pizzaria.model.entity.*;
import com.company.pizzaria.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ConfirmacaoPedidoController {

	private final PedidoService pedidoService;

	public ConfirmacaoPedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping("/confirmacao-pedido/{id}")
	public String exibirPaginaConfirmacaoPedido(@PathVariable Long id, Model model) {
		Pedido pedido = pedidoService.buscarPorId(id)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));

		model.addAttribute("pedido", pedido);
		return "confirmacao-pedido";
	}
}
