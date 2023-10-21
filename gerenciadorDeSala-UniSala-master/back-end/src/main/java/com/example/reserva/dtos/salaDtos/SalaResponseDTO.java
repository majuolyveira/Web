package com.example.reserva.dtos.salaDtos;
import com.example.reserva.models.Sala;

public record SalaResponseDTO(Long id, String identificacao, String descricao, String categoria, String predio, String piso) {
    public SalaResponseDTO(Sala sala) {
         this(sala.getId(), sala.getIdentificacao(), sala.getDescricao(),
                 sala.getCategoria(), sala.getPredio(), sala.getPiso());
    }
}
