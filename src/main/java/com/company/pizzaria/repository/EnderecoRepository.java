package com.company.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.pizzaria.model.entity.Endereco;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}