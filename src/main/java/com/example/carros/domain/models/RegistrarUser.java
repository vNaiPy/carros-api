package com.example.carros.domain.models;

import lombok.Data;

@Data
public class RegistrarUser {
    private String login;
    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;

}
