package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pizzaria.model.entity.Endereco;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    // Busca todos os endereços de um cliente específico
    List<Endereco> findByClienteId(Long clienteId);

    // Busca endereços por CEP
    List<Endereco> findByCep(String cep);

    // Busca endereços por cidade
    List<Endereco> findByCidade(String cidade);

    // Busca endereços por estado
    List<Endereco> findByEstado(String estado);

    // Busca endereços por cidade e estado
    List<Endereco> findByCidadeAndEstado(String cidade, String estado);

    // Busca endereços por bairro
    List<Endereco> findByBairro(String bairro);

    // Busca endereços que contenham parte do logradouro (case insensitive)
    List<Endereco> findByLogradouroContainingIgnoreCase(String parteLogradouro);
}