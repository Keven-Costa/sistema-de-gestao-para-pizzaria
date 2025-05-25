package com.company.pizzaria.model.enums;

public enum StatusPedidos {
    PENDENTE("Pendente"),
    PROCESSANDO("Em preparo"),
    ENTREGUE("Entregue"),
    ENVIADO("Enviado"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedidos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}