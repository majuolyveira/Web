package com.example.reserva.dtos.salaDtos;

import com.example.reserva.models.Categoria;
import com.example.reserva.models.Localizacao;

public record SalaRequestDTO(String identificacao, String descricao, String categoria, String predio, String piso) {

}
