package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pizzaria.model.entity.Promocao;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

}
