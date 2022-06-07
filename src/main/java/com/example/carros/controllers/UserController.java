package com.example.carros.controllers;

import com.example.carros.controllers.dto.UserDTO;
import com.example.carros.domain.models.RegistrarUser;
import com.example.carros.domain.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastrar")
public class UserController {

    private UserService _userService;

    public UserController(UserService userService){
        this._userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> registrarUsuario(@RequestBody RegistrarUser registrarUser){
        try{
            return ResponseEntity.ok(_userService.registrarUsuario(registrarUser));
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
