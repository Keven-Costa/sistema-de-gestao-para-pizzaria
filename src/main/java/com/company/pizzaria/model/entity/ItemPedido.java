package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = false)
	private String nome;

	@Column(name = "preco_unitario", nullable = false)
	private Double precoUnitario;

}
