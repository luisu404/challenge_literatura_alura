package com.luisu404.challengeliteratura.principal;

import com.luisu404.challengeliteratura.dto.AutorDTO;
import com.luisu404.challengeliteratura.dto.LibroDTO;
import com.luisu404.challengeliteratura.model.Autor;
import com.luisu404.challengeliteratura.model.Libro;
import com.luisu404.challengeliteratura.service.AutorService;
import com.luisu404.challengeliteratura.service.ConvierteDatos;
import com.luisu404.challengeliteratura.service.LibroService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    Scanner lectura = new Scanner(System.in);
    private final LibroService libroService;
    private final AutorService autorService;
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Libro libro = new Libro();
    private LibroDTO libroDTO;
    private Autor autor = new Autor();
    private AutorDTO autorDTO;

    public Principal(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }
    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {

            var menu = """
                =================================================================
                *    1. Buscar libro por titulo                                 *
                *    2. Mostrar libros guardados                                *
                *    3. Mostrar autores guardados                               *
                *    4. Mostrar autor vivo en un determinado año                *
                *    5. Mostrar cantidad de libros en un determinado idioma     *
                *    0. Salir                                                   *
                =================================================================
                """;

            System.out.print(menu);
            System.out.print("Elige una opción: --> ");

            String entrada = lectura.nextLine();

            // Validar que solo sean números
            if (!entrada.matches("\\d+")) {
                System.out.println("Debe ingresar solo números del menú.\n");
                continue;
            }

            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> mostarLibrosGuardados();
                case 3 -> mostrarAutoresRegistrados();
                case 4 -> mostarAutoresVivosPorAnio();
                case 5 -> mostrarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida.\n");
            }
        }
    }
    private void buscarLibroPorTitulo(){
        System.out.print("Escribe el nombre del libro: ");
        System.out.print("--> ");

        var nombreLibro = lectura.nextLine();
        Optional<LibroDTO> libroBuscado = libroService.getDatosLibro(nombreLibro);

        if (libroBuscado.isPresent()) {
            libroDTO = libroBuscado.get();
            System.out.println("""
                    ==========================================
                              📘 INFORMACIÓN LIBRO
                    ==========================================
                    """);
            System.out.println(libroDTO);
            System.out.println("==========================================");
            libro  = convierteDatos.convertirLibroAEntidad(libroDTO);

            if (libroService.findByTitulo(libro.getTitulo()).isPresent()) {
                System.out.println("El libro ya existe en la base de datos.");
            }

            else
            {
                String opcionGuardado;
                do {
                    System.out.println("¿Desea guardar el libro en la base de datos?");
                    System.out.println("'S' para Sí o 'N' para No: ---> "   );
                    opcionGuardado = lectura.nextLine().trim().toUpperCase();

                    if (!opcionGuardado.equals("S") && !opcionGuardado.equals("N")) {
                        System.out.println("Opción inválida. Debe escribir 'S' o 'N'.\n");
                    }

                } while (!opcionGuardado.equals("S") && !opcionGuardado.equals("N"));

                if (opcionGuardado.equals("S")) {
                    boolean confirmacionGuardado = libroService.guardarLibro(libro);
                    if (confirmacionGuardado){
                        System.out.println("Libro guardado correctamente.");
                    } else {
                        System.out.println("El libro no se pudo guardar.");
                    }
                } else {
                    System.out.println("Libro no guardado.");
                }
            }
        }
        else{
            System.out.println("Libro no encontrado");
        }
    }
    private void mostrarLibrosPorIdioma() {

        List<LibroDTO> libros = libroService.mostrarTodosLosLibros();

        List<String> idiomasDisponibles = libros.stream()
                .flatMap(libro -> libro.idiomas().stream())
                .distinct()
                .sorted()
                .toList();

        if (idiomasDisponibles.isEmpty()) {
            System.out.println("No hay idiomas registrados.");
            return;
        }

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\n=== MENÚ DE IDIOMAS ===");

            for (int i = 0; i < idiomasDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + idiomasDisponibles.get(i));
            }

            System.out.println("0. Volver al menú principal");
            System.out.print("--> ");

            String entrada = lectura.nextLine().trim();

            // Validar solo números
            if (!entrada.matches("\\d+")) {
                System.out.println("Debe ingresar solo números.");
                continue;
            }

            opcion = Integer.parseInt(entrada);

            if (opcion == 0) {
                System.out.println("Volviendo al menú principal...");
                break;
            }

            if (opcion < 1 || opcion > idiomasDisponibles.size()) {
                System.out.println("Opción inválida.");
                continue;
            }

            String idiomaSeleccionado = idiomasDisponibles.get(opcion - 1);

            List<LibroDTO> librosFiltrados = libros.stream()
                    .filter(libro -> libro.idiomas().contains(idiomaSeleccionado))
                    .toList();

            if (librosFiltrados.isEmpty()) {
                System.out.println("No hay libros en ese idioma.");
            } else {
                System.out.println("\nTotal libros en " + idiomaSeleccionado + ": " + librosFiltrados.size());
                librosFiltrados.forEach(System.out::println);
            }
        }
    }

    private void mostarAutoresVivosPorAnio() {
        System.out.print("Escribe el año: ");
        System.out.print("--> ");
        String anio = lectura.nextLine();
        List<AutorDTO> autores = autorService.mostarAutoresViviosByYear(anio);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            autores.forEach(System.out::println);
        }
    }

      private void mostarLibrosGuardados() {
        List<LibroDTO> libroDTOList = libroService.mostrarTodosLosLibros();
        System.out.println("==========================================");
        System.out.println("LISTA DE LIBROS GUARDADOS");
        System.out.println("==========================================");
        int i = 1;
        for (LibroDTO libroDTO : libroDTOList){
            System.out.println("#"+i);
            System.out.println(libroDTO.toString());
            i++;
        }
    }

    private void mostrarAutoresRegistrados() {
        List<AutorDTO> autorDTOList = autorService.mostrarTodosLosAutores();
        System.out.println("==========================================");
        System.out.println("LISTA DE AUTORES REGISTRADOS");
        System.out.println("==========================================");
        int i = 1;
        for (AutorDTO autorDTO : autorDTOList){
            System.out.println("#"+i + " - " + autorDTO.toString());
            i++;
        }
    }




}
