package com.company.pizzaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private String codigo;        // Pode ser o id ou outro código
    private String sabores;       // String que mostra os sabores da pizza (ex: "Calabresa, Margherita")
    private String statusCss;     // Classe CSS para status (ex: "status-preparo", "status-entregue")
    private String statusTexto;   // Texto do status (ex: "Em preparo", "Entregue")
    
    // getters e setters
}

