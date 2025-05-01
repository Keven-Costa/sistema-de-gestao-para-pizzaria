package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.pizzaria.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Verifica se email já existe
    boolean existsByEmail(String email);
    
    // Verifica se telefone já existe
    boolean existsByTelefone(String telefone);
    
    // Adicione este método se precisar verificar por CPF também
    boolean existsByCpf(String cpf);
}