package um.programacion2.util;

import um.programacion2.libro.Libro;
import um.programacion2.libro.EstadoLibro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestDataFactory {
    public static List<Libro> createLibros() {
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1L, "978-0-306-40615-7", "El Quijote", "Miguel de Cervantes", EstadoLibro.DISPONIBLE));
        libros.add(new Libro(2L, "9-876-54321-0", "Cien años de soledad", "Gabriel García Márquez", EstadoLibro.EN_REPARACION));
        libros.add(new Libro(3L, "0-123-45678-9", "1984", "George Orwell", EstadoLibro.PRESTADO));
        return libros;
    }

}