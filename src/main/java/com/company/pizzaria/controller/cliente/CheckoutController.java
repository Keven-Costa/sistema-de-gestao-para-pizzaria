package com.company.pizzaria.controller.cliente;

import com.company.pizzaria.dto.CheckoutDTO;
import com.company.pizzaria.dto.ItemDTO;
import com.company.pizzaria.model.entity.*;
import com.company.pizzaria.model.enums.StatusPedidos;
import com.company.pizzaria.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ItemPedidoService itemPedidoService;



	@GetMapping
	public String mostrarPaginaCheckout(Model model) {
		model.addAttribute("checkoutDTO", new CheckoutDTO());
		return "checkout";
	}
	
//	@PostMapping("/salvar")
//	public ResponseEntity<?> salvarPedido(@RequestBody String body) {
//	    System.out.println("Recebi o corpo da requisição: " + body);
//	    // Tente converter depois, ou só pra debug mesmo
//	    return ResponseEntity.ok("Recebido");
//	}
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvarPedido(@RequestBody CheckoutDTO dto) {
		try {
			// Criar e salvar cliente
			Cliente cliente = new Cliente();
			cliente.setNome(dto.getNome());
			cliente.setTelefone(dto.getTelefone());
			cliente = clienteService.salvar(cliente);

			// Criar e salvar endereço
			Endereco endereco = new Endereco();
			endereco.setCep(dto.getCep());
			endereco.setRua(dto.getRua());
			endereco.setNumero(dto.getNumero());
			endereco.setBairro(dto.getBairro());
			endereco.setReferencia(dto.getReferencia());
			endereco.setCliente(cliente);
			endereco = enderecoService.salvar(endereco);

			// Criar pedido
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setEndereco(endereco);
			pedido.setObservacoes(dto.getObservacoes());
			pedido.setData(LocalDateTime.now());
			pedido.setStatus(StatusPedidos.PENDENTE);
			pedido.setPrecoTotal(dto.getPreco_total());
			pedido = pedidoService.salvarPedido(pedido);

			// Salvar itens
			for (ItemDTO itemDto : dto.getItens()) {
				ItemPedido item = new ItemPedido();
				item.setNome(itemDto.getNome());
				item.setQuantidade(itemDto.getQuantidade());
				item.setPrecoUnitario(itemDto.getPreco());
				item.setPedido(pedido);


				itemPedidoService.salvar(item);
			}

			// Depois de salvar tudo, monte um resumo para devolver:
        Map<String, Object> resumoPedido = new HashMap<>();
        resumoPedido.put("cliente", cliente.getNome());
        resumoPedido.put("telefone", cliente.getTelefone());
        resumoPedido.put("endereco", endereco.getRua() + ", " + endereco.getNumero() + " - " + endereco.getBairro());
        resumoPedido.put("observacoes", pedido.getObservacoes());
        resumoPedido.put("dataPedido", pedido.getData().toString());
        resumoPedido.put("precoTotal", pedido.getPrecoTotal());
        
        // Itens do pedido para o resumo
        List<Map<String, Object>> itensResumo = new ArrayList<>();
        for (ItemPedido item : itemPedidoService.buscarPorPedido(pedido)) { // crie esse método se não existir
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("nome", item.getNome());
            itemMap.put("quantidade", item.getQuantidade());
            itemMap.put("preco", item.getPrecoUnitario());
            itensResumo.add(itemMap);
        }
        resumoPedido.put("itens", itensResumo);

        // Retorna o resumo junto com a mensagem e url
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Pedido salvo com sucesso!");
        response.put("redirectUrl", "/confirmacao-pedido");
        response.put("resumo", resumoPedido);

        return ResponseEntity.ok(response);

    } catch (Exception e) {
        e.printStackTrace();
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Erro ao salvar pedido: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
	}
	
//	@PostMapping("/salvar")
//	public ResponseEntity<?> salvarPedido(@RequestBody CheckoutDTO dto) {
//		try {
//			// Criar e salvar cliente
//			Cliente cliente = new Cliente();
//			cliente.setNome(dto.getNome());
//			cliente.setTelefone(dto.getTelefone());
//			cliente = clienteService.salvar(cliente);
//
//			// Criar e salvar endereço
//			Endereco endereco = new Endereco();
//			endereco.setCep(dto.getCep());
//			endereco.setRua(dto.getRua());
//			endereco.setNumero(dto.getNumero());
//			endereco.setBairro(dto.getBairro());
//			endereco.setReferencia(dto.getReferencia());
//			endereco.setCliente(cliente);
//			endereco = enderecoService.salvar(endereco);
//
//			// Criar pedido
//			Pedido pedido = new Pedido();
//			pedido.setCliente(cliente);
//			pedido.setEndereco(endereco);
//			pedido.setObservacoes(dto.getObservacoes());
//			pedido.setData(LocalDateTime.now());
//			pedido.setStatus("PENDENTE");
//			pedido.setPrecoTotal(dto.getPreco_total());
//			pedido = pedidoService.salvarPedido(pedido);
//
//			// Salvar itens
//			for (ItemDTO itemDto : dto.getItens()) {
//				ItemPedido item = new ItemPedido();
//				item.setNome(itemDto.getNome());
//				item.setQuantidade(itemDto.getQuantidade());
//				item.setPrecoUnitario(itemDto.getPreco());
//				item.setPedido(pedido);
//
//
//				itemPedidoService.salvar(item);
//			}
//
//			Map<String, String> response = new HashMap<>();
//			response.put("message", "Pedido salvo com sucesso!");
//			response.put("redirectUrl", "/confirmacao-pedido	");
//
//			return ResponseEntity.ok(response);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			Map<String, String> errorResponse = new HashMap<>();
//			errorResponse.put("error", "Erro ao salvar pedido: " + e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//		}
//	}
}


