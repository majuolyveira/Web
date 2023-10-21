package com.example.reserva.repositories;
import com.example.reserva.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

}
