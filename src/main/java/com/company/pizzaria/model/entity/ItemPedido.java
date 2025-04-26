//package com.company.pizzaria.model.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import java.math.BigDecimal;
//import java.util.Objects;
//
//@Entity
//@Table(name = "itens_pedido")
//public class ItemPedido {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "item_pedido_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "pedido_id", nullable = false)
//    private Pedido pedido;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "pizza_id")
//    private Pizza pizza;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "tamanho_id", nullable = false)
//    private Tamanho tamanho;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "metade1_pizza_id")
//    private Pizza metade1;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "metade2_pizza_id")
//    private Pizza metade2;
//
//    @NotNull(message = "A quantidade é obrigatória")
//    @Min(value = 1, message = "A quantidade mínima é 1")
//    @Max(value = 10, message = "A quantidade máxima é 10")
//    @Column(name = "quantidade", nullable = false)
//    private Integer quantidade;
//
//    @Transient
//    private BigDecimal precoUnitario;
//
//    @Transient
//    private BigDecimal precoTotal;
//
//    // Construtores
//    public ItemPedido() {
//    }
//
//    public ItemPedido(Pedido pedido, Pizza pizza, Tamanho tamanho, Pizza metade1, Pizza metade2, Integer quantidade) {
//        this.pedido = pedido;
//        this.pizza = pizza;
//        this.tamanho = tamanho;
//        this.metade1 = metade1;
//        this.metade2 = metade2;
//        this.quantidade = quantidade;
//    }
//
//    // Getters e Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Pedido getPedido() {
//        return pedido;
//    }
//
//    public void setPedido(Pedido pedido) {
//        this.pedido = pedido;
//    }
//
//    public Pizza getPizza() {
//        return pizza;
//    }
//
//    public void setPizza(Pizza pizza) {
//        this.pizza = pizza;
//    }
//
//    public Tamanho getTamanho() {
//        return tamanho;
//    }
//
//    public void setTamanho(Tamanho tamanho) {
//        this.tamanho = tamanho;
//    }
//
//    public Pizza getMetade1() {
//        return metade1;
//    }
//
//    public void setMetade1(Pizza metade1) {
//        this.metade1 = metade1;
//    }
//
//    public Pizza getMetade2() {
//        return metade2;
//    }
//
//    public void setMetade2(Pizza metade2) {
//        this.metade2 = metade2;
//    }
//
//    public Integer getQuantidade() {
//        return quantidade;
//    }
//
//    public void setQuantidade(Integer quantidade) {
//        this.quantidade = quantidade;
//    }
//
//    // Método para calcular preço unitário
//    public BigDecimal getPrecoUnitario() {
//        if (precoUnitario == null) {
//            calcularPrecos();
//        }
//        return precoUnitario;
//    }
//
//    // Método para calcular preço total
//    public BigDecimal getPrecoTotal() {
//        if (precoTotal == null) {
//            calcularPrecos();
//        }
//        return precoTotal;
//    }
//
//    // Lógica de cálculo de preços
////    private void calcularPrecos() {
////        if (pizza != null) {
////            // Pizza inteira
////            precoUnitario = pizza.getPrecoBase().multiply(tamanho.getMultiplicadorPreco());
////        } else if (metade1 != null && metade2 != null) {
////            // Pizza meia-meia (média dos preços)
////            BigDecimal precoMetade1 = metade1.getPrecoBase().multiply(tamanho.getMultiplicadorPreco());
////            BigDecimal precoMetade2 = metade2.getPrecoBase().multiply(tamanho.getMultiplicadorPreco());
////            precoUnitario = precoMetade1.add(precoMetade2).divide(BigDecimal.valueOf(2));
////        } else {
////            precoUnitario = BigDecimal.ZERO;
////        }
////        
////        precoTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
////    }
//
//    // equals e hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ItemPedido that = (ItemPedido) o;
//        return Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    // toString
//   // @Override
////    public String toString() {
////        return "ItemPedido{" +
////                "id=" + id +
////                ", pedido=" + pedido.getId() +
////                ", pizza=" + (pizza != null ? pizza.getNome() : "null") +
////                ", tamanho=" + tamanho.getNome() +
////                ", metade1=" + (metade1 != null ? metade1.getNome() : "null") +
////                ", metade2=" + (metade2 != null ? metade2.getNome() : "null") +
////                ", quantidade=" + quantidade +
////                '}';
////    }
//}