package com.example.carros.controllers.dto;

import com.example.carros.domain.models.Mensagem;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
public class MensagemDTO {
    private String mensagem;
    private LocalDate localDate;
    private Long toUserId;


    public static MensagemDTO createMensagemDTO(Mensagem mensagem){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mensagem, MensagemDTO.class);
    }
}
