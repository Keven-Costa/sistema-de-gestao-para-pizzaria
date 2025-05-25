package com.company.pizzaria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.company.pizzaria.model.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
