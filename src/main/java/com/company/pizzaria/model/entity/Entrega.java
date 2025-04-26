package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.company.pizzaria.model.enums.StatusEntrega;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entregaId;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    private String entregador;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private LocalDateTime previsaoChegada;

    // Getters e Setters

    public Long getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(Long entregaId) {
        this.entregaId = entregaId;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getEntregador() {
        return entregador;
    }

    public void setEntregador(String entregador) {
        this.entregador = entregador;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public LocalDateTime getPrevisaoChegada() {
        return previsaoChegada;
    }

    public void setPrevisaoChegada(LocalDateTime previsaoChegada) {
        this.previsaoChegada = previsaoChegada;
    }
}

