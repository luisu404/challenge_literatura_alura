# Challenge Literatura - Cursos Alura
# Literatura Challenge | Oracle ONE + Alura Latam

Aplicación de consola desarrollada con **Java + Spring Boot** como parte del programa **Oracle Next Education (ONE)** junto a **Alura Latam**.

Permite consumir una API de libros, almacenar la información en PostgreSQL y realizar consultas dinámicas sobre libros y autores.

---

## 🚀 Stack

- Java 17  
- Spring Boot  
- Spring Data JPA  
- PostgreSQL  
- Maven  

---

## 🧱 Arquitectura

Principal (Consola)  
↓  
Service  
↓  
Repository (JPA)  
↓  
PostgreSQL  

- Separación por capas  
- Uso de DTO
- Consultas JPQL y NativeQuery  

---

## 📖 Funcionalidades

1. Buscar libro por título  
2. Mostrar libros guardados  
3. Mostrar autores guardados  
4. Mostrar autores vivos en un año  
5. Mostrar libros por idioma  
 

### ✔ Buscar libro
- Consulta API externa  
- Guarda libro y autor evitando duplicados  

### ✔ Autores vivos por año
- Compara año ingresado con nacimiento y fallecimiento  

### ✔ Libros por idioma
- Menú dinámico de idiomas  
- Filtrado con Streams  
- Conteo automático  

---

## 🎯 Objetivo

Aplicar:

- Persistencia con JPA  
- Consumo de API REST  
- Arquitectura en capas  
- Buenas prácticas backend  

---

## 👨‍💻 Autor

Luis Urbaez (luisu404)
Oracle Next Education - Alura Latam  
