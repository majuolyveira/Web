package com.example.reserva.controllers;

import com.example.reserva.dtos.categoriaDtos.CategoriaRequestDTO;
import com.example.reserva.models.Categoria;
import com.example.reserva.dtos.categoriaDtos.CategoriaResponseDTO;
import com.example.reserva.models.Localizacao;
import com.example.reserva.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public  Categoria criarCategoria(@RequestBody CategoriaRequestDTO newCategoria) {
        Categoria newSalaModel = new Categoria(newCategoria);
        return categoriaRepository.save(newSalaModel);
    }

    @GetMapping
    public List<CategoriaResponseDTO> getAll() {
        List<CategoriaResponseDTO> categorias = categoriaRepository.
                findAll().stream().map(CategoriaResponseDTO::new).toList();
        return categorias;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable Long id) {
        try {
            Categoria categoriaDeletada = categoriaRepository.getReferenceById(id);
            categoriaRepository.delete(categoriaDeletada);
            return ResponseEntity.ok("categoria deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir categoria, essa categoria está associada a uma Sala: " + e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCategoria(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.setNome(categoriaRequestDTO.nome());
        categoriaRepository.save(categoria);
        return ResponseEntity.ok("categoria atualizada com sucesso");
    }
}
