package com.example.carros.domain.services;

import com.example.carros.controllers.dto.MensagemDTO;
import com.example.carros.domain.models.Mensagem;

import java.util.List;

public interface MensagemService {

    List<MensagemDTO> listMessageReceived();

    MensagemDTO sendMessage(Mensagem mensagem);

    List<MensagemDTO> listMessageBetweenCurrentUserAndUserId(Long toUserId);
}
