package com.company.pizzaria.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String nome;
    private int quantidade;
    private Double preco;
    private Long produtoId; // só o id do produto

    
}
