package com.example.reserva.controllers;

import com.example.reserva.dtos.categoriaDtos.CategoriaResponseDTO;
import com.example.reserva.dtos.localizacaoDtos.LocalizacaoRequestDTO;
import com.example.reserva.dtos.localizacaoDtos.LocalizacaoResponseDTO;
import com.example.reserva.dtos.salaDtos.SalaRequestDTO;
import com.example.reserva.models.Localizacao;
import com.example.reserva.models.Sala;
import com.example.reserva.repositories.LocalizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteLocalizacao(@PathVariable Long id) {
        try {
            Localizacao localizacaoDel = localizacaoRepository.getReferenceById(id);
            localizacaoRepository.delete(localizacaoDel);
            return ResponseEntity.ok("Localizacao deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir localizacao, essa localização está associada a uma Sala: " + e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateLocalizacao(@PathVariable Long id, @RequestBody LocalizacaoRequestDTO localizacaoRequestDTO) {
        Localizacao localizacao = localizacaoRepository.getReferenceById(id);
        localizacao.setPredio(localizacaoRequestDTO.predio());
        localizacao.setPiso(localizacaoRequestDTO.piso());
        localizacaoRepository.save(localizacao);
        return ResponseEntity.ok("localizacao atualizada com sucesso");
    }
}
