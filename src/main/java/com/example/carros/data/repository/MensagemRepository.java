package com.example.carros.data.repository;

import com.example.carros.domain.models.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByUserIdAndToUserId(Long userId, Long toUserId);

    List<Mensagem> findByToUserId(Long toUserId);
}
