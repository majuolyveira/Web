package com.example.reserva.models;
import com.example.reserva.dtos.salaDtos.SalaRequestDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSala;
    @Column(name = "identificacao")
    private String identificacao;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "categoria")
    private String categoria;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idLocalizacao")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Localizacao localizacao;

    public Sala(){

    }

    public Sala(SalaRequestDTO sala, Localizacao localizacao) {
        this.identificacao = sala.identificacao();
        this.descricao = sala.descricao();
        this.categoria = sala.categoria();
        this.localizacao = localizacao;
    }

    public Long getId() {
        return idSala;
    }

    public void setId(Long id) {
        this.idSala = id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
