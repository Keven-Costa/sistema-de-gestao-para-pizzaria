package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pizzaria.model.entity.Adicional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AdicionalRepository extends JpaRepository<Adicional, Long> {
    
    List<Adicional> findByDisponivelTrue();
    
    List<Adicional> findByNomeContainingIgnoreCase(String nome);
    
    boolean existsByNomeIgnoreCase(String nome);
    
    List<Adicional> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);
    
    List<Adicional> findByPrecoLessThanEqual(BigDecimal precoMax);
}