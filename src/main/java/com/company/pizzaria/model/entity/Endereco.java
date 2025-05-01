package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    @NotBlank
    @Size(max = 10)
    @Column(name = "cep", nullable = false, length = 10)
    private String cep;
    
    @NotBlank
    @Size(max = 100)
    @Column(name = "rua", nullable = false, length = 100)
    private String rua;
    
    @NotBlank
    @Size(max = 10)
    @Column(name = "numero", nullable = false, length = 10)
    private String numero;
    
    @NotBlank
    @Size(max = 50)
    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @Size(max = 100)
    @Column(name = "referencia", length = 100)
    private String referencia;
    

    public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	// Construtores
    public Endereco() {
    }

    public Endereco(Cliente cliente, String logradouro, String numero, String bairro, 
                   String cidade, String estado, String cep) {
        this.cliente = cliente;
        this.numero = numero;
        this.bairro = bairro;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", referencia='" + referencia + '\'' +
                '}';
    }
}