package com.luisu404.challengeliteratura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisu404.challengeliteratura.dto.AutorDTO;
import com.luisu404.challengeliteratura.dto.LibroDTO;
import com.luisu404.challengeliteratura.model.Autor;
import com.luisu404.challengeliteratura.model.Libro;

import java.util.List;

public class ConvierteDatos implements IConvierteDatos {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convierteDatosLibroDeJsonAClase(String json, Class<T> clase) {
        try {
            return mapper.readValue(json.toString(), clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    public LibroDTO convertirLibroADTO(Libro libro) {

        List<AutorDTO> autoresDTO = libro.getAutores()
                .stream()
                .map(autor -> new AutorDTO(
                        autor.getId(),
                        autor.getNombre(),
                        autor.getAnioNacimiento(),
                        autor.getAnioFallecimiento()
                ))
                .toList();

        return new LibroDTO(
                libro.getId(),
                libro.getTitulo(),
                autoresDTO,
                libro.getIdiomas(),
                libro.getCantDescargas()
        );
    }


    public Libro convertirLibroAEntidad(LibroDTO dto) {

        Libro libro = new Libro();

        //libro.setId(dto.id());
        libro.setTitulo(dto.titulo());
        libro.setIdiomas(dto.idiomas());
        libro.setCantDescargas(dto.cantDescargas());

        if (dto.autores() != null) {
            List<Autor> autores = dto.autores()
                    .stream()
                    .map(a -> {
                        Autor autor = new Autor();
                        autor.setId(a.id());
                        autor.setNombre(a.nombre());
                        autor.setAnioNacimiento(a.anioNacimiento());
                        autor.setAnioFallecimiento(a.anioFallecimiento());
                        return autor;
                    })
                    .toList();

            libro.setAutores(autores);
        }

        return libro;
    }


    public AutorDTO convertirAutorADTO(Autor autor) {
        return new AutorDTO(
                autor.getId(),
                autor.getNombre(),
                autor.getAnioNacimiento(),
                autor.getAnioFallecimiento()
        );
    }

    public Autor convertirAutorAEntidad(AutorDTO dto) {
        Autor autor = new Autor();

        //autor.setId(a.id());
        autor.setNombre(dto.nombre());
        autor.setAnioNacimiento(dto.anioNacimiento());
        autor.setAnioFallecimiento(dto.anioFallecimiento());

        return autor;
    }

}
