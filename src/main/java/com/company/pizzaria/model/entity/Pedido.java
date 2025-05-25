package com.company.pizzaria.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.company.pizzaria.model.enums.StatusPedidos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
	
    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;
	
    @Column(nullable = false)
    private LocalDateTime data;
	
    @Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 100)
	private StatusPedidos status;
	
    @Column(name = "forma_pagamento")
    private String formaPagamento;
    
    @Column(length = 500)
    private String observacoes;
    
    @Column(name = "preco_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoTotal;
    
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
}