package com.example.carros.domain.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String nome;
    private String tipo;

}
