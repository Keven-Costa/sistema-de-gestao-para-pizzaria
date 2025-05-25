package com.company.pizzaria.repository;


import com.company.pizzaria.model.entity.ItemPedido;
import com.company.pizzaria.model.entity.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	List<ItemPedido> findByPedido(Pedido pedido);
    
}

