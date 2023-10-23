package com.example.reserva.models;
import com.example.reserva.dtos.localizacaoDtos.LocalizacaoRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "localizacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalizacao;
    @Column(name = "predio")
    private String predio;
    @Column(name = "piso")
    private String piso;
   // @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL)
   // private List<Sala> salas;



    public Localizacao(LocalizacaoRequestDTO localizacaoRequestDTO){
        this.predio = localizacaoRequestDTO.predio();
        this.piso = localizacaoRequestDTO.piso();
    }

    public Long getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(Long idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    //public List<Sala> getSalas() {
     //   return salas;
    //}

    //public void setSalas(List<Sala> salas) {
     //   this.salas = salas;
   // }
}