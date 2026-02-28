package com.luisu404.challengeliteratura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record AutorDTO(
        Long id,
        @JsonAlias("name")
        String nombre,
        @JsonAlias("birth_year")
        String anioNacimiento,
        @JsonAlias("death_year")
        String anioFallecimiento
) {


    @Override
    public String toString() {
        return "%s (%s - %s)".formatted(
              nombre,
              anioNacimiento == null ? "Desconocido" : anioNacimiento,
              anioFallecimiento == null ? "Desconocido" : anioFallecimiento
        );
    }
}