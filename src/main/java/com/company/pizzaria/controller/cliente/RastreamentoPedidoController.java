package com.company.pizzaria.controller.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rastreamento-pedido")
public class RastreamentoPedidoController {
	
	@GetMapping()
	public String exibirPaginaRastreamentoPedido() {
		return "rastreamento-pedido";
	}
}
