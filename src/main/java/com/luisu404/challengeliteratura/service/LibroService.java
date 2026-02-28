package com.luisu404.challengeliteratura.service;
import com.luisu404.challengeliteratura.dto.LibroDTO;
import com.luisu404.challengeliteratura.model.Autor;
import com.luisu404.challengeliteratura.model.DatosLibros;
import com.luisu404.challengeliteratura.model.Idioma;
import com.luisu404.challengeliteratura.model.Libro;
import com.luisu404.challengeliteratura.repository.IAutorRepository;
import com.luisu404.challengeliteratura.repository.ILibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {

    @Autowired
    private ILibroRepository libroRepository;
    private IAutorRepository autorRepository;
    private AutorService autorService;
    public LibroService(ILibroRepository libroRepository, AutorService autorService, IAutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorService = autorService;
        this.autorRepository = autorRepository;
    }
    private static final String URL_BASE = "https://gutendex.com/books/";

    private ConsumoApiService apiService = new ConsumoApiService();
    private ConvierteDatos convierteDatos = new ConvierteDatos();;
    private Libro libro = new Libro();
    Scanner lectura = new Scanner(System.in);



    public Optional<LibroDTO> getDatosLibro(String nombreLibro){
       var jsonLibro = apiService.obtenerDatosfromApi(URL_BASE+"?search="+nombreLibro.replace(" ","+"));
       var librosEncontrados = convierteDatos.convierteDatosLibroDeJsonAClase(jsonLibro, DatosLibros.class);
         Optional<LibroDTO> libroBuscado = librosEncontrados.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
    return libroBuscado;

    }

public Optional<Libro> findByTitulo(String tituloLibro){
        return libroRepository.findByTitulo(tituloLibro);
}


    @Transactional
    public Boolean guardarLibro(Libro libro) {

        List<Autor> autoresFinales = new ArrayList<>();

        for (Autor autor : libro.getAutores()) {

            Optional<Autor> autorExistente =
                    autorService.findByNombreIgnoreCase(autor.getNombre());

            if (autorExistente.isPresent()) {
                autoresFinales.add(autorExistente.get());
            } else {
                Autor nuevoAutor = autorRepository.save(autor);
                autoresFinales.add(nuevoAutor);
            }
        }

        libro.setAutores(autoresFinales);

        // 🔥 Convertir idiomas
        if (libro.getIdiomas() != null) {
            List<String> idiomasConvertidos = libro.getIdiomas()
                    .stream()
                    .map(Idioma::obtenerNombreCompleto)
                    .toList();

            libro.setIdiomas(idiomasConvertidos);
        }

        var confirmacion = libroRepository.save(libro);
        return confirmacion.getId() != null;
    }

    public List<LibroDTO> mostrarTodosLosLibros() {
        List<Libro> libros = libroRepository.findAllConAutores();
        List<LibroDTO> libroDTOS = new ArrayList<>();
        for (Libro libro : libros){
            LibroDTO libroDTO = convierteDatos.convertirLibroADTO(libro);
            libroDTOS.add(libroDTO);
        }
        return libroDTOS;
    }
}