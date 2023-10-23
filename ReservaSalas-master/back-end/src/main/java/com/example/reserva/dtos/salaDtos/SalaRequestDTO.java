package com.example.reserva.dtos.salaDtos;

public record SalaRequestDTO(String identificacao, String descricao, String categoria, Long localizacao) {

    public String getIdentificacao() {
        return identificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Long getLocalizacao() {
        return localizacao;
    }
}

