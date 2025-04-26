package com.company.pizzaria.model.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sabores")
public class Sabor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sabor")
    private Long id;

    @NotBlank(message = "O nome do sabor é obrigatório")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Size(max = 255)
    @Column(name = "imagem_url", length = 255)
    private String imagemUrl;

    @Column(name = "disponivel", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean disponivel = true;

    // Construtores
    public Sabor() {
    }

    public Sabor(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Método para desativar o sabor
    public void desativar() {
        this.disponivel = false;
    }

    // Método para ativar o sabor
    public void ativar() {
        this.disponivel = true;
    }

    // toString
    @Override
    public String toString() {
        return "Sabor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagemUrl='" + imagemUrl + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}