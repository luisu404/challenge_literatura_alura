package com.luisu404.challengeliteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luisu404.challengeliteratura.dto.LibroDTO;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("results") List<LibroDTO> resultados
) {
}
