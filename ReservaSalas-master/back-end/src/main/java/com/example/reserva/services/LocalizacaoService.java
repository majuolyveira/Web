package com.example.reserva.services;

import com.example.reserva.models.Localizacao;
import com.example.reserva.repositories.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public Localizacao obterLocalizacaoPorId(Long id) {
        return localizacaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Localizacao não encontrada com o id: " + id));
    }
}

