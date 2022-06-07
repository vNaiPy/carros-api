package com.example.carros.domain.services;

import com.example.carros.controllers.dto.CarroDTO;
import com.example.carros.domain.models.Carro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarroService {

    List<CarroDTO> listCarro();

    List<CarroDTO> listCarroByCurrentUser();

    Optional<CarroDTO> getCarroById(Long id);

    List<CarroDTO> listCarroByTipo(String tipo);

    CarroDTO addCarro(Carro carro);

    CarroDTO updateCarro(Long id, Carro carro);

    boolean removeCarro(Long id);

}
