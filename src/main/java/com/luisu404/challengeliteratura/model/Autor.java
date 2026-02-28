package com.luisu404.challengeliteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private String anioNacimiento;

    @JsonAlias("death_year")
    private String anioFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAnioNacimiento() { return anioNacimiento; }
    public void setAnioNacimiento(String anioNacimiento) {
        if (anioNacimiento != null && !anioNacimiento.isEmpty()) {
            this.anioNacimiento = anioNacimiento;
        } else {
            this.anioNacimiento = "Desconocido";
        }
    }

    public String getAnioFallecimiento() { return anioFallecimiento; }
    public void setAnioFallecimiento(String anioFallecimiento) {
        if (anioFallecimiento != null && !anioFallecimiento.isEmpty()) {
            this.anioFallecimiento = anioFallecimiento;
        } else {
            this.anioFallecimiento = "Desconocido";
        }
    }

    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }

}

