package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;  // Importe a classe BigDecimal

@Entity
@Table(name = "ingredientes")
@Getter
@Setter
public class Ingrediente {
	public Ingrediente() {}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingrediente_id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "quantidade_atual", precision = 10, scale = 2)
    private BigDecimal quantidadeAtual;  
    
	@Column(name = "unidade_medida", length = 20)
    private String unidadeMedida;

    @Column(name = "estoque_minimo", precision = 10, scale = 2)
    private BigDecimal estoqueMinimo;

	@Column(name = "status", length = 20)
    private String status;

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

	public BigDecimal getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(BigDecimal quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public BigDecimal getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}