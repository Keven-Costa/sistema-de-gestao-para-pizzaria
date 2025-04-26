package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pizzaria.model.entity.Pizza;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    
    List<Pizza> findByTamanhoId(Long tamanhoId);
    
    List<Pizza> findBySabor1IdOrSabor2Id(Long sabor1Id, Long sabor2Id);
    
    List<Pizza> findByBordaRecheadaTrue();
    
    List<Pizza> findByPrecoFinalBetween(BigDecimal precoMin, BigDecimal precoMax);
}