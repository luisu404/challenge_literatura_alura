package com.luisu404.challengeliteratura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luisu404.challengeliteratura.model.Autor;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public record LibroDTO(
        Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<AutorDTO> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double cantDescargas
) {

    @Override
    public String toString() {

        String autoresFormateados;

        if (autores == null || autores.isEmpty()) {
            autoresFormateados = "No disponible \n1";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < autores.size(); i++) {
                sb.append(" %d) Nombre: %s \n".formatted(i + 1, autores.get(i)));
            }

            autoresFormateados = sb.toString();
        }

        return """ 
            📖 Título: %s
            👤 Autor(es):
            %s 🌎 Idioma: %s
             ⬇ Descargado : %.0f veces
            """.formatted(
                titulo,
                autoresFormateados,
                (idiomas == null || idiomas.isEmpty()) ? "N/D" : idiomas.get(0),
                cantDescargas == null ? 0 : cantDescargas
        );
    }
}