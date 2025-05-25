package com.company.pizzaria.repository;

import com.company.pizzaria.model.entity.Pizza;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
	Optional<Pizza> findByNome(String nome);
}
