package com.company.pizzaria.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.company.pizzaria.model.entity.Tamanho;

public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {
    Optional<Tamanho> findByNome(Tamanho.NomeTamanho nome);
}