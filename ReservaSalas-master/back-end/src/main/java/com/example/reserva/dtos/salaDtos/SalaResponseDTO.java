package com.example.reserva.dtos.salaDtos;
import com.example.reserva.models.Localizacao;
import com.example.reserva.models.Sala;

public record SalaResponseDTO(
        Long id,
        String identificacao,
        String descricao,
        String categoria,
        Localizacao localizacao
) {




    @Override
    public Long id() {
        return id;
    }

    @Override
    public String identificacao() {
        return identificacao;
    }

    @Override
    public String descricao() {
        return descricao;
    }

    @Override
    public String categoria() {
        return categoria;
    }

    @Override
    public Localizacao localizacao() {
        return localizacao;
    }




}
