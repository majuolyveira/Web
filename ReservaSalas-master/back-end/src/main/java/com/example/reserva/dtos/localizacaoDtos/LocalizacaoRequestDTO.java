package com.example.reserva.dtos.localizacaoDtos;

public record LocalizacaoRequestDTO(String predio, String piso) {

    @Override
    public String predio() {
        return predio;
    }

    @Override
    public String piso() {
        return piso;
    }
}
