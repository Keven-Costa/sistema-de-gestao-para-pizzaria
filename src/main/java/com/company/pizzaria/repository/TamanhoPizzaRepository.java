package com.company.pizzaria.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pizzaria.model.entity.TamanhoPizza;

@Repository
public interface TamanhoPizzaRepository extends JpaRepository<TamanhoPizza, Long> {
    
    List<TamanhoPizza> findByDisponivelTrue();
    
    List<TamanhoPizza> findByNomeContainingIgnoreCase(String nome);
    
    boolean existsByNomeIgnoreCase(String nome);
    
    List<TamanhoPizza> findByPrecoBaseBetween(BigDecimal precoMin, BigDecimal precoMax);
    
    List<TamanhoPizza> findByFatias(Integer fatias);
}
