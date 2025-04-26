package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza")
    private Long id;

    @NotNull(message = "O tamanho da pizza é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tamanho", nullable = false)
    private TamanhoPizza tamanho;

    @NotNull(message = "O primeiro sabor é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sabor1", nullable = false)
    private Sabor sabor1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sabor2")
    private Sabor sabor2;

    @Column(name = "borda_recheada", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean bordaRecheada = false;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @NotNull(message = "O preço final é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "O preço deve ter no máximo 8 dígitos inteiros e 2 decimais")
    @Column(name = "preco_final", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoFinal;

    // Construtores
    public Pizza() {
    }

    public Pizza(TamanhoPizza tamanho, Sabor sabor1, BigDecimal precoFinal) {
        this.tamanho = tamanho;
        this.sabor1 = sabor1;
        this.precoFinal = precoFinal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TamanhoPizza getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoPizza tamanho) {
        this.tamanho = tamanho;
    }

    public Sabor getSabor1() {
        return sabor1;
    }

    public void setSabor1(Sabor sabor1) {
        this.sabor1 = sabor1;
    }

    public Sabor getSabor2() {
        return sabor2;
    }

    public void setSabor2(Sabor sabor2) {
        this.sabor2 = sabor2;
    }

    public Boolean getBordaRecheada() {
        return bordaRecheada;
    }

    public void setBordaRecheada(Boolean bordaRecheada) {
        this.bordaRecheada = bordaRecheada;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public BigDecimal getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(BigDecimal precoFinal) {
        this.precoFinal = precoFinal;
    }

    // Métodos de negócio
    public boolean isMeiaSabor() {
        return sabor2 != null;
    }

    // toString
    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", tamanho=" + (tamanho != null ? tamanho.getNome() : null) +
                ", sabor1=" + (sabor1 != null ? sabor1.getNome() : null) +
                ", sabor2=" + (sabor2 != null ? sabor2.getNome() : null) +
                ", bordaRecheada=" + bordaRecheada +
                ", precoFinal=" + precoFinal +
                '}';
    }
}























