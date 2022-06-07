package com.example.carros.controllers;

import com.example.carros.controllers.dto.MensagemDTO;
import com.example.carros.domain.models.Mensagem;
import com.example.carros.domain.services.MensagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messenger")
public class MensagemController {

    private MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService){
        this.mensagemService = mensagemService;
    }

    @GetMapping("/list-message-received")
    public ResponseEntity<List<MensagemDTO>> listMessageReceived(){
        List<MensagemDTO> listMensagem = mensagemService.listMessageReceived();

        return listMensagem.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(listMensagem);
    }

    @GetMapping("/list-message")
    public ResponseEntity<List<MensagemDTO>> listMessageBetweenCurrentUserAndUserId(@RequestParam("to-user-id") Long toUserId){
        List<MensagemDTO> listMensagem = mensagemService.listMessageBetweenCurrentUserAndUserId(toUserId);

        return listMensagem.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(listMensagem);
    }

    @PostMapping
    public ResponseEntity sendMessage(@RequestBody Mensagem mensagem){
        try{
            MensagemDTO mensagemDTO = mensagemService.sendMessage(mensagem);
            return ResponseEntity.ok(mensagemDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
