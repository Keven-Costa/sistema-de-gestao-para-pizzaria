package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @NotBlank
    @Size(max = 100)
    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @NotBlank
    @Size(max = 10)
    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Size(max = 50)
    @Column(name = "complemento", length = 50)
    private String complemento;

    @NotBlank
    @Size(max = 50)
    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @NotBlank
    @Size(max = 50)
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @NotBlank
    @Size(max = 10)
    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Size(max = 100)
    @Column(name = "referencia", length = 100)
    private String referencia;

    // Construtores
    public Endereco() {
    }

    public Endereco(Cliente cliente, String logradouro, String numero, String bairro, 
                   String cidade, String estado, String cep) {
        this.cliente = cliente;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    // toString
    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cliente=" + (cliente != null ? cliente.getId() : null) +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", referencia='" + referencia + '\'' +
                '}';
    }
}