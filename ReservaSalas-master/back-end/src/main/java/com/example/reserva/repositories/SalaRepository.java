package com.example.reserva.repositories;
import com.example.reserva.models.Localizacao;
import com.example.reserva.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    List<Sala> findByLocalizacao(Localizacao localizacao);
}
