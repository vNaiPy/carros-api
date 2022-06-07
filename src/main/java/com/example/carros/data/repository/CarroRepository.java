package com.example.carros.data.repository;

import com.example.carros.domain.models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);

    List<Carro> findByUserId(Long userId);
}
