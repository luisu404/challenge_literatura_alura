package com.luisu404.challengeliteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @JsonAlias("title")
    private String titulo;


    @JsonAlias("authors")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Double cantDescargas;

    public Libro(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<Autor> getAutores() { return autores; }
    public void setAutores(List<Autor> autores) { this.autores = autores; }

    public List<String> getIdiomas() {  return idiomas; }

    public void setIdiomas(List<String> idiomas) {
        if (idiomas != null && !idiomas.isEmpty()){
            String idiomaLimpio = idiomas.get(0)
                    .replace("{", "")
                    .replace("}", "");
            this.idiomas = List.of(idiomaLimpio);        }
        else {
            this.idiomas = idiomas;
        }
    }

    public Double getCantDescargas() { return cantDescargas; }
    public void setCantDescargas(Double cantDescargas) { this.cantDescargas = cantDescargas; }



}
