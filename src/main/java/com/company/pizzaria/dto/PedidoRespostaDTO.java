package com.company.pizzaria.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRespostaDTO {
	private String nome;
	private String telefone;
	private String endereco;
	private String referencia;

	private List<ItemDTO> itens;

	private PagamentoDTO metodoPagamento;
	private BigDecimal valorTotal;
	private BigDecimal trocoPara;
	private String status;
}
