package com.example.carros.controllers.dto;

import com.example.carros.domain.models.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarroDTO {
    private Long id;
    private String nome;
    private String tipo;
    private UserDTO userDTO;

    public static CarroDTO createCarroDTO(Carro carro){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }
}
