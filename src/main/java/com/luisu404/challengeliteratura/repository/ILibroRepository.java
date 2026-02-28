package com.luisu404.challengeliteratura.repository;


import com.luisu404.challengeliteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT DISTINCT l FROM Libro l " +
            "LEFT JOIN FETCH l.autores order by l.id asc")
    List<Libro> findAllConAutores();

    List<Libro> findByTituloContainsIgnoreCase(String nombreLibro);
    Optional<Libro> findByTitulo(String titulo);
    Optional<Libro> findByTituloIgnoreCase(String titulo);

    @Query(value = "SELECT * FROM libros l JOIN libro_idiomas li ON l.id = li.libro_id WHERE li.idiomas = :idioma",nativeQuery = true)
    List<Libro> buscarPorIdioma(@Param("idioma") String idioma);

    @Query( value = "SELECT l.* FROM libros l INNER JOIN libro_autor la ON l.id = la.libro_id INNER JOIN autores a ON la.autor_id = a.id WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombreAutor, '%'))", nativeQuery = true)
    List<Libro> buscarLibrosPorAutor(@Param("nombreAutor") String nombreAutor);


}
