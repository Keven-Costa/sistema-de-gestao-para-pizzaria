package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pizzaria.model.entity.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}