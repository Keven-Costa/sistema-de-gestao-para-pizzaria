package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pizzaria.model.entity.Sabor;

import java.util.List;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
    
    List<Sabor> findByDisponivelTrue();
    
    List<Sabor> findByNomeContainingIgnoreCase(String nome);
    
    boolean existsByNomeIgnoreCase(String nome);
}