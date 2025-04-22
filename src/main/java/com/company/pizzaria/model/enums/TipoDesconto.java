package com.company.pizzaria.model.enums;

public enum TipoDesconto {
    PORCENTAGEM("Porcentagem"),
    VALOR_FIXO("Valor Fixo");

    private final String descricao;

    TipoDesconto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoDesconto fromDescricao(String descricao) {
        for (TipoDesconto tipo : TipoDesconto.values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de desconto desconhecido: " + descricao);
    }
}