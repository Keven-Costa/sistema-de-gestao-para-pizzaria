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
public class CheckoutDTO {
    private String nome;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String telefone;
    private String referencia;
    private String observacoes;
    private PagamentoDTO pagamento;
    private BigDecimal preco_total;


    private List<ItemDTO> itens; // Enviar itens do pedido aqui

}
