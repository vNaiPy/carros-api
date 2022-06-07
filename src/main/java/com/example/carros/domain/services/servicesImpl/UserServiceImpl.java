package com.example.carros.domain.services.servicesImpl;

import com.example.carros.controllers.dto.UserDTO;
import com.example.carros.data.repository.RoleRepository;
import com.example.carros.data.repository.UserRepository;
import com.example.carros.domain.models.RegistrarUser;
import com.example.carros.domain.models.Role;
import com.example.carros.domain.models.User;
import com.example.carros.domain.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public UserDTO registrarUsuario(RegistrarUser registrarUser) {
        if(registrarUser.getSenha().equals(registrarUser.getConfirmarSenha()))
            throw new IllegalArgumentException("As senha são diferentes");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Role role = roleRepository.findById(1L).get();
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = new User();
        user.setLogin(registrarUser.getLogin());
        user.setEmail(registrarUser.getEmail());
        user.setNome(registrarUser.getNome());
        user.setSenha(encoder.encode(registrarUser.getSenha()));
        user.setRoles(roles);

        return UserDTO.create(userRepository.save(user));
    }
}
