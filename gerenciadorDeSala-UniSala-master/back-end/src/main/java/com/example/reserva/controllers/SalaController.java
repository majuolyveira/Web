package com.example.reserva.controllers;
import com.example.reserva.dtos.salaDtos.SalaRequestDTO;
import com.example.reserva.dtos.salaDtos.SalaResponseDTO;
import com.example.reserva.repositories.SalaRepository;
import com.example.reserva.models.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")

public class SalaController {


    @Autowired
    private SalaRepository salaRepository;

    @PostMapping
    public Sala criaSala(@RequestBody SalaRequestDTO sala){
        Sala listaDeSalas = new Sala(sala);
        return salaRepository.save(listaDeSalas);
    }

    @GetMapping
    public List<SalaResponseDTO> getAll(){

        List<SalaResponseDTO> salaList = salaRepository.findAll()
                .stream().map(SalaResponseDTO::new).toList();

        return salaList;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSala(@PathVariable Long id) {
        try {
            salaRepository.deleteById(id);
            return new ResponseEntity<>("Sala excluída com sucesso", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Sala não encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro ao excluir a sala", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> updateSala(@PathVariable Long id, @RequestBody SalaRequestDTO salaRequestDTO) {
        Optional<Sala> salaOptional = salaRepository.findById(id);

        if (salaOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Sala existingSala = salaOptional.get();

        // Atualize os campos relevantes com base nos dados do DTO
        existingSala.setIdentificacao(salaRequestDTO.identificacao());
        existingSala.setDescricao(salaRequestDTO.descricao());
        existingSala.setCategoria(salaRequestDTO.categoria());
        existingSala.setPredio(salaRequestDTO.predio());
        existingSala.setPiso(salaRequestDTO.piso());

        // Salve a sala atualizada no repositório
        Sala updatedSala = salaRepository.save(existingSala);

        // Converta a sala atua lizada para um DTO de resposta
        SalaResponseDTO salaResponseDTO = new SalaResponseDTO(updatedSala);

        return new ResponseEntity<>(salaResponseDTO, HttpStatus.OK);
    }





}
