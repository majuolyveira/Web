package com.example.reserva.models;
import com.example.reserva.dtos.categoriaDtos.CategoriaRequestDTO;
import jakarta.persistence.*;


@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;
    @Column(name = "nome")
    private String nome;

    public Categoria() {

    }

    public Categoria(CategoriaRequestDTO categoriaRequest) {
        this.nome = categoriaRequest.nome();
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

