package com.example.reserva.models;
import com.example.reserva.dtos.salaDtos.SalaRequestDTO;
import jakarta.persistence.*;

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
    @Column(name = "predio")
    private String predio;
    @Column(name = "piso")
    private String piso;

    /*@ManyToOne
    @JoinColumn(name ="id_categoria") // Coluna de chave estrangeira para a categoria (Representa a categoria da sala)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name ="id_localizacao") // coluna de chave estrangeira para localização (Representa a LOCALIZAÇÃO da sala)
    private Localizacao localizacao;
    */
    public Sala(){

    }
    public Sala(SalaRequestDTO sala){
        this.identificacao = sala.identificacao();
        this.descricao = sala.descricao();
        this.categoria = sala.categoria();
        this.predio = sala.predio();
        this.piso = sala.piso();
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
}
