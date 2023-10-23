package com.example.reserva.services;

import com.example.reserva.models.Sala;
import com.example.reserva.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala criarSala(Sala sala) {
        return salaRepository.save(sala);
    }
}