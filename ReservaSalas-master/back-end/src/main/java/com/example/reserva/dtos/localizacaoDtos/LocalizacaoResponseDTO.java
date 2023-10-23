package com.example.reserva.dtos.localizacaoDtos;

import com.example.reserva.models.Localizacao;

public record LocalizacaoResponseDTO(Long id, String predio, String piso) {
    public LocalizacaoResponseDTO(Localizacao localizacao) {
        this(localizacao.getIdLocalizacao(),
                localizacao.getPredio(), localizacao.getPiso());
    }
}
