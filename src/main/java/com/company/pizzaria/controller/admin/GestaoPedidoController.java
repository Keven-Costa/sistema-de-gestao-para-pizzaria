package com.company.pizzaria.controller.admin;

import com.company.pizzaria.dto.ItemDTO;
import com.company.pizzaria.dto.PagamentoDTO;
import com.company.pizzaria.dto.PedidoRespostaDTO;
import com.company.pizzaria.model.entity.Pedido;
import com.company.pizzaria.model.enums.StatusPedidos;
import com.company.pizzaria.repository.PedidoRepository;
import com.company.pizzaria.service.PedidoService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/gestao-pedidos")
public class GestaoPedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping()
	public String exibirPaginaGestaoPedido(Model model) {
		List<Pedido> pedidos = pedidoRepository.findAll();
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("novoPedido", new Pedido());

		return "admin/gestao-pedidos";
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoRespostaDTO> getPedidoPorId(@PathVariable Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		PedidoRespostaDTO dto = new PedidoRespostaDTO();
		dto.setNome(pedido.getCliente().getNome());
		dto.setTelefone(pedido.getCliente().getTelefone());
		dto.setStatus(pedido.getStatus().name());

		String enderecoCompleto = String.format("%s, nº %s, bairro %s, CEP %s", pedido.getEndereco().getRua(),
				pedido.getEndereco().getNumero(), pedido.getEndereco().getBairro(), pedido.getEndereco().getCep());
		dto.setEndereco(enderecoCompleto);

		dto.setReferencia(pedido.getEndereco().getReferencia());

		dto.setItens(pedido.getItens().stream()
				.map(item -> new ItemDTO(item.getNome(), item.getQuantidade(), item.getPrecoUnitario(), null))
				.collect(Collectors.toList()));

		PagamentoDTO pagamentoDTO = new PagamentoDTO();
		pagamentoDTO.setTipo(pedido.getFormaPagamento() != null ? pedido.getFormaPagamento().name() : null);

		pagamentoDTO.setTrocoPara(null);
		dto.setMetodoPagamento(pagamentoDTO);

		dto.setValorTotal(pedido.getPrecoTotal());
		dto.setTrocoPara(null);

		return ResponseEntity.ok(dto);
	}

	@PostMapping("/{id}/status")
	public ResponseEntity<Void> atualizarStatusPedido(@PathVariable Long id, @RequestBody Map<String, String> request) {

		String novoStatus = request.get("status");
		pedidoService.atualizarStatusPedido(id, StatusPedidos.valueOf(novoStatus));
		return ResponseEntity.ok().build();
	}

}
