package com.example.reserva.controllers;
import com.example.reserva.dtos.salaDtos.SalaRequestDTO;
import com.example.reserva.dtos.salaDtos.SalaResponseDTO;
import com.example.reserva.models.Localizacao;
import com.example.reserva.models.Sala;
import com.example.reserva.repositories.LocalizacaoRepository;
import com.example.reserva.repositories.SalaRepository;
import com.example.reserva.services.LocalizacaoService;
import com.example.reserva.services.SalaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin()
@RestController
@RequestMapping("/salas")

public class SalaController {


    @Autowired
    private SalaService salaService;

    @Autowired
    private LocalizacaoService localizacaoService;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private LocalizacaoRepository localizacaoRepository;
    private static final Logger logger = LoggerFactory.getLogger(SalaController.class);


    @PostMapping("{id}")
    public Sala criarSala(@RequestBody SalaRequestDTO salaRequestDTO, @PathVariable Long id) {
        logger.info("ID de localizacao recebido: {}", id);
        Localizacao localizacao = localizacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localizacao não encontrada com o id: " + id));
        Sala novaSala = new Sala();
        novaSala.setIdentificacao(salaRequestDTO.getIdentificacao());
        novaSala.setDescricao(salaRequestDTO.getDescricao());
        novaSala.setCategoria(salaRequestDTO.getCategoria());
        novaSala.setLocalizacao(localizacao);

        return salaRepository.save(novaSala);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSala(@PathVariable Long id) {
        Sala sala = salaRepository.getReferenceById(id);

        // Remove a referência da Localizacao
        Localizacao localizacao = sala.getLocalizacao();
        sala.setLocalizacao(null);
        salaRepository.save(sala); // Atualiza a sala sem a referência de Localizacao


        // Finalmente, exclui a Sala
        salaRepository.delete(sala);

        return ResponseEntity.ok("Sala excluída com sucesso");
    }

    @GetMapping
    public List<SalaResponseDTO> getAllSalas() {
        List<Sala> salas = salaRepository.findAll();
        List<SalaResponseDTO> salaResponseDTOs = new ArrayList<>();
        for (Sala sala : salas) {
            salaResponseDTOs.add(new SalaResponseDTO(
                    sala.getId(),
                    sala.getIdentificacao(),
                    sala.getDescricao(),
                    sala.getCategoria(),
                    sala.getLocalizacao()
            ));
        }
        return salaResponseDTOs;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateSala(@PathVariable Long id, @RequestBody SalaRequestDTO salaRequestDTO) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada com o id: " + id));

        sala.setIdentificacao(salaRequestDTO.getIdentificacao());
        sala.setDescricao(salaRequestDTO.getDescricao());
        sala.setCategoria(salaRequestDTO.getCategoria());

        // Atualiza a localização da sala se a nova localização não for nula
        if (salaRequestDTO.getLocalizacao() != null) {
            Localizacao localizacao = localizacaoRepository.findById(salaRequestDTO.getLocalizacao())
                    .orElseThrow(() -> new EntityNotFoundException("Localização não encontrada com o id: " + salaRequestDTO.getLocalizacao()));
            sala.setLocalizacao(localizacao);
        }

        salaRepository.save(sala);

        return ResponseEntity.ok("Sala atualizada com sucesso");
    }

}
