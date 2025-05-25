package com.company.pizzaria.service;

import com.company.pizzaria.model.entity.ItemPedido;
import com.company.pizzaria.model.entity.Pedido;
import com.company.pizzaria.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> listarTodos() {
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedido> buscarPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    public ItemPedido salvar(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public void deletar(Long id) {
        itemPedidoRepository.deleteById(id);
    }
    
    public List<ItemPedido> buscarPorPedido(Pedido pedido) {
        return itemPedidoRepository.findByPedido(pedido);
    }
}

