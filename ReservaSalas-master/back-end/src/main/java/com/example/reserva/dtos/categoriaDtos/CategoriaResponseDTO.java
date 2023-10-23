package com.example.reserva.dtos.categoriaDtos;

import com.example.reserva.models.Categoria;

public record CategoriaResponseDTO(Long id, String nome) {
    public CategoriaResponseDTO(Categoria categoria){

        this(categoria.getIdCategoria(), categoria.getNome());
    }
}
