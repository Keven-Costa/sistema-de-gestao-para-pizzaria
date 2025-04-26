package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TamanhosPizza")
public class TamanhoPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tamanho")
    private Long id;

    @NotBlank(message = "O nome do tamanho é obrigatório")
    @Size(max = 20, message = "O nome deve ter no máximo 20 caracteres")
    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @Size(max = 50, message = "A descrição deve ter no máximo 50 caracteres")
    @Column(name = "descricao", length = 50)
    private String descricao;

    @NotNull(message = "O preço base é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    @Digits(integer = 8, fraction = 2, message = "O preço deve ter no máximo 8 dígitos inteiros e 2 decimais")
    @Column(name = "preco_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoBase;

    @NotNull(message = "O número de fatias é obrigatório")
    @Min(value = 1, message = "Deve ter pelo menos 1 fatia")
    @Max(value = 16, message = "Não pode ter mais que 16 fatias")
    @Column(name = "fatias", nullable = false)
    private Integer fatias;

    @Column(name = "disponivel", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean disponivel = true;

    // Construtores
    public TamanhoPizza() {
    }

    public TamanhoPizza(String nome, BigDecimal precoBase, Integer fatias) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.fatias = fatias;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public Integer getFatias() {
        return fatias;
    }

    public void setFatias(Integer fatias) {
        this.fatias = fatias;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Métodos de negócio
    public void desativar() {
        this.disponivel = false;
    }

    public void ativar() {
        this.disponivel = true;
    }

    // toString
    @Override
    public String toString() {
        return "TamanhoPizza{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoBase=" + precoBase +
                ", fatias=" + fatias +
                ", disponivel=" + disponivel +
                '}';
    }
}