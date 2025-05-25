package com.company.pizzaria.controller.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfirmacaoPeidoController {
	
	@GetMapping("confirmacao-pedido")
	public String exibirPaginaConfirmacaoPedido() {
		return "confirmacao-pedido";
	}
}
