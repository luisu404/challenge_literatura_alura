package com.luisu404.challengeliteratura.repository;

import com.luisu404.challengeliteratura.model.Autor;
import com.luisu404.challengeliteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Long> {

    @Query("""
    SELECT a FROM Autor a WHERE a.anioNacimiento <> 'Desconocido'
    AND CAST(a.anioNacimiento AS integer) <= :anio AND (a.anioFallecimiento = 'Desconocido'
    OR CAST(a.anioFallecimiento AS integer) >= :anio)
    """)
    List<Autor> buscarAutoresVivosEnAnio(@Param("anio") Integer anio);


    Optional<Autor> findByNombreIgnoreCase(@Param("nombre") String nombre);

}
