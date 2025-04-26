package com.company.pizzaria.model.enums;

public enum StatusPedido {
    PENDENTE,       // Aguardando pagamento/confirmação
    EM_PREPARO,    // Em preparação
    ENVIADO,        // Saiu para entrega
    ENTREGUE,       // Finalizado com sucesso
    CANCELADO,     // Pedido cancelado
	ATRASADO,
	NOVO;
}