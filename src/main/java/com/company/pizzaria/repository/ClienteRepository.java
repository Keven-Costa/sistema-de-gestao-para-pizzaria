package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.pizzaria.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}