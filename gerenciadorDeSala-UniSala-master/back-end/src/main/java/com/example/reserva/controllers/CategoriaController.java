package com.example.reserva.controllers;

import com.example.reserva.dtos.categoriaDtos.CategoriaRequestDTO;
import com.example.reserva.models.Categoria;
import com.example.reserva.dtos.categoriaDtos.CategoriaResponseDTO;
import com.example.reserva.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository CategoriaRepository;

    @PostMapping
    public  Categoria criarCategoria(@RequestBody CategoriaRequestDTO newCategoria) {
        Categoria newSalaModel = new Categoria(newCategoria);
        return CategoriaRepository.save(newSalaModel);
    }

    @GetMapping
    public List<CategoriaResponseDTO> getAll() {
        List<CategoriaResponseDTO> categorias = CategoriaRepository.
                findAll().stream().map(CategoriaResponseDTO::new).toList();
        return categorias;
    }

}
