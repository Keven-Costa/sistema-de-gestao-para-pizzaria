package com.company.pizzaria.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.company.pizzaria.dto.ItemDTO;
import com.company.pizzaria.dto.RastreamentoPedidoDTO;

import com.company.pizzaria.model.entity.ItemPedido;
import com.company.pizzaria.model.entity.Pedido;
import com.company.pizzaria.model.enums.StatusPedidos;
import com.company.pizzaria.repository.ItemPedidoRepository;
import com.company.pizzaria.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ItemPedidoRepository itemPedidoRepository;

	public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.itemPedidoRepository = itemPedidoRepository;
	}

	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}

	public Optional<Pedido> buscarPorId(Long id) {
		return pedidoRepository.findById(id);
	}

	public Pedido salvarPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public void deletarPedido(Long id) {
		pedidoRepository.deleteById(id);
	}

	public int contarPedidosHoje() {
		LocalDateTime inicio = LocalDate.now().atStartOfDay();
		LocalDateTime fim = LocalDateTime.now();
		return pedidoRepository.countByDataBetween(inicio, fim);
	}

	public BigDecimal valorTotalPedidosHoje() {
		LocalDateTime inicio = LocalDate.now().atStartOfDay();
		LocalDateTime fim = LocalDateTime.now();
		return pedidoRepository.somarValorTotalPorPeriodo(inicio, fim).orElse(BigDecimal.ZERO);
	}

	public List<Pedido> ultimosPedidos() {
		return pedidoRepository.findTop10ByOrderByDataDesc();
	}

	public void atualizarStatusPedido(Long id, StatusPedidos novoStatus) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		pedido.setStatus(novoStatus);
		pedidoRepository.save(pedido);
	}

	public RastreamentoPedidoDTO getRastreamentoPedido(Long id) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

		List<ItemDTO> itens = pedido.getItens().stream().map(item -> {
			ItemDTO dtoItem = new ItemDTO();
			dtoItem.setNome(item.getNome());
			dtoItem.setQuantidade(item.getQuantidade());
			dtoItem.setPreco(item.getPrecoUnitario());
			return dtoItem;
		}).toList();

		RastreamentoPedidoDTO dto = new RastreamentoPedidoDTO();
		dto.setId(pedido.getId());
		dto.setStatus(pedido.getStatus().name());
		dto.setStatusDescricao(pedido.getStatus().getDescricao());
		dto.setItens(itens);
		dto.setTotal(pedido.getPrecoTotal().doubleValue());

		return dto;
	}

	public Map<String, Long> obterSaboresMaisVendidos() {
		List<ItemPedido> itens = itemPedidoRepository.findAll();

		Map<String, Long> contagemSabores = itens.stream()
				.collect(Collectors.groupingBy(ItemPedido::getNome, Collectors.counting()));

		return contagemSabores.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.limit(5)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
