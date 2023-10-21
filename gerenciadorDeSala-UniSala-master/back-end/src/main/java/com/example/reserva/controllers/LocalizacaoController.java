package com.example.reserva.controllers;

import com.example.reserva.dtos.categoriaDtos.CategoriaResponseDTO;
import com.example.reserva.dtos.localizacaoDtos.LocalizacaoRequestDTO;
import com.example.reserva.dtos.localizacaoDtos.LocalizacaoResponseDTO;
import com.example.reserva.models.Localizacao;
import com.example.reserva.repositories.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/localizacao")
@CrossOrigin(origins = "*")
public class LocalizacaoController {
    @Autowired
    private LocalizacaoRepository localizacaoRepository;
    @PostMapping
    public Localizacao criarLocalizacao(@RequestBody LocalizacaoRequestDTO newLocalizacao) {
        Localizacao localizacoes = new Localizacao(newLocalizacao);
        return localizacaoRepository.save(localizacoes);
    }

    @GetMapping
    public List<LocalizacaoResponseDTO> getAll(){
        List<LocalizacaoResponseDTO> localizacaoList = localizacaoRepository.findAll().stream().map(LocalizacaoResponseDTO::new).toList();
        return localizacaoList;
    }
}
