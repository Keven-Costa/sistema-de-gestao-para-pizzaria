package com.company.pizzaria.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pizzas")
public class Pizza extends Produto {

    @Column(length = 50)
    private String tipo;

    @Column(length = 255)
    private String imagem;

}
