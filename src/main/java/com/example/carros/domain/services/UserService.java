package com.example.carros.domain.services;

import com.example.carros.controllers.dto.UserDTO;
import com.example.carros.domain.models.RegistrarUser;

public interface UserService {
    UserDTO registrarUsuario(RegistrarUser registrarUser);
}
