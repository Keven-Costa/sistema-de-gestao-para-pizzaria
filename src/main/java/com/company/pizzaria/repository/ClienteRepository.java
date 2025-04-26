package com.company.pizzaria.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.company.pizzaria.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByTelefone(String telefone);
    Optional<Cliente> findByEmail(String email);
}