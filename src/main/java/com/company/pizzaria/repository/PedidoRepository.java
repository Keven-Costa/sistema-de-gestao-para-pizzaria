package com.company.pizzaria.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.pizzaria.model.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	// Corrigido - últimos 10 pedidos
	List<Pedido> findTop10ByOrderByDataDesc();

	// Pedidos de hoje
	int countByDataBetween(LocalDateTime start, LocalDateTime end);

	@Query("SELECT SUM(p.precoTotal) FROM Pedido p WHERE p.data >= :inicio AND p.data <= :fim")
	Optional<BigDecimal> somarValorTotalPorPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
