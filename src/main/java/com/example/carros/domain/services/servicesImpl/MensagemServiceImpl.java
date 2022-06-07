package com.example.carros.domain.services.servicesImpl;

import com.example.carros.data.repository.MensagemRepository;
import com.example.carros.controllers.dto.MensagemDTO;
import com.example.carros.data.repository.UserRepository;
import com.example.carros.domain.models.Mensagem;
import com.example.carros.domain.models.User;
import com.example.carros.domain.services.MensagemService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensagemServiceImpl implements MensagemService {

    private MensagemRepository _mensagemRepository;
    private UserRepository _userRepository;

    public MensagemServiceImpl(MensagemRepository mensagemRepository, UserRepository userRepository) {
        this._mensagemRepository = mensagemRepository;
        this._userRepository = userRepository;
    }

    public List<MensagemDTO> listMessageReceived(){
        User currentUser = getIdCurrentUser();

        return _mensagemRepository.findByToUserId(currentUser.getId())
                .stream().map(MensagemDTO::createMensagemDTO)
                .collect(Collectors.toList());
    }

    public List<MensagemDTO> listMessageBetweenCurrentUserAndUserId(Long toUserId) {
        User currentUser = getIdCurrentUser();

        return _mensagemRepository.findByUserIdAndToUserId(currentUser.getId(), toUserId)
                .stream().map(MensagemDTO::createMensagemDTO)
                .collect(Collectors.toList());
    }

    public MensagemDTO sendMessage(Mensagem mensagem) {
        if(!_userRepository.existsById(mensagem.getToUserId()))
            throw new IllegalArgumentException("Destinátario inexistente!");

        User currentUser = getIdCurrentUser();

        Mensagem novaMensagem = new Mensagem();
        novaMensagem.setMensagem(mensagem.getMensagem());
        novaMensagem.setLocalDate(LocalDate.now());
        novaMensagem.setToUserId(mensagem.getToUserId());
        novaMensagem.setUser(currentUser);

        return MensagemDTO.createMensagemDTO(_mensagemRepository.save(novaMensagem));
    }

    private User getIdCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
