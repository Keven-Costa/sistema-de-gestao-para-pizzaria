package com.company.pizzaria.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RastreamentoPedidoDTO {
	private Long id;
	private String status;
	private String statusDescricao;
	private String previsaoEntrega;
	private LocalDateTime horaRecebido;
	private LocalDateTime horaInicioPreparo;
	private LocalDateTime horaSaida;
	private LocalDateTime horaEntrega;
	private List<ItemDTO> itens;
	private Double total;
}
