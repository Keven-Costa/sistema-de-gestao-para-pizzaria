package com.company.pizzaria.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.pizzaria.model.entity.Pedido;
import com.company.pizzaria.repository.PedidoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;

	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
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

}
