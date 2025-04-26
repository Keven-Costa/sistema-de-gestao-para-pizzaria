package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tamanhos")
public class Tamanho {

    public enum NomeTamanho {
        BROTO,
        MEDIA,
        GRANDE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tamanho_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false, length = 10)
    private NomeTamanho nome;

    @NotNull(message = "O multiplicador de preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O multiplicador deve ser maior que zero")
    @Digits(integer = 2, fraction = 2, message = "O multiplicador deve ter no máximo 2 dígitos inteiros e 2 decimais")
    @Column(name = "multiplicador_preco", nullable = false, precision = 4, scale = 2)
    private BigDecimal multiplicadorPreco;

    // Construtores
    public Tamanho() {
    }

    public Tamanho(NomeTamanho nome, BigDecimal multiplicadorPreco) {
        this.nome = nome;
        this.multiplicadorPreco = multiplicadorPreco;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NomeTamanho getNome() {
        return nome;
    }

    public void setNome(NomeTamanho nome) {
        this.nome = nome;
    }

    public BigDecimal getMultiplicadorPreco() {
        return multiplicadorPreco;
    }

    public void setMultiplicadorPreco(BigDecimal multiplicadorPreco) {
        this.multiplicadorPreco = multiplicadorPreco;
    }

    // equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tamanho tamanho = (Tamanho) o;
        return Objects.equals(id, tamanho.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "Tamanho{" +
                "id=" + id +
                ", nome=" + nome +
                ", multiplicadorPreco=" + multiplicadorPreco +
                '}';
    }
}