package com.company.pizzaria.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pizzaria.model.entity.Promocao;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
	List<Promocao> findByStatus(String status);

	List<Promocao> findByValidadeBetween(LocalDate start, LocalDate end);

	Optional<Promocao> findByCodigo(String codigo);

}