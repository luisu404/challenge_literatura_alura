package com.luisu404.challengeliteratura.service;

import com.luisu404.challengeliteratura.dto.AutorDTO;
import com.luisu404.challengeliteratura.model.Autor;
import com.luisu404.challengeliteratura.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private IAutorRepository autorRepository;

    public AutorService(IAutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }
    private ConvierteDatos convierteDatos = new ConvierteDatos();;
    private Autor autor = new Autor();

    public List<AutorDTO> mostrarTodosLosAutores() {
        List<Autor> autores = autorRepository.findAll();
        List<AutorDTO> autorDTOS = new ArrayList<>();
        for (Autor autor : autores){
            AutorDTO autorDTO = convierteDatos.convertirAutorADTO(autor);
            autorDTOS.add(autorDTO);
        }
        return autorDTOS;
    }
    public Optional<Autor> findByNombreIgnoreCase(String nombre){
        return autorRepository.findByNombreIgnoreCase(nombre);
    }

    public List<AutorDTO> mostarAutoresViviosByYear(String year) {
        Integer anio;
        try {
            anio = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            System.out.println("El año debe ser numérico.");
            return List.of();
        }
        List<Autor> autores = autorRepository.buscarAutoresVivosEnAnio(anio);
        return autores.stream()
                .map(convierteDatos::convertirAutorADTO)
                .toList();
    }

}
