package com.luisu404.challengeliteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    @JsonAlias("title")
    public String titulo;

//    @JsonAlias("authors")
//    public List<Autor> autor;

    @JsonAlias("languages")
    public List<String> idiomas;

    @JsonAlias("download_count")
    public Double cantDescargas;





}
