package um.programacion2.util;

import um.programacion2.libro.Libro;
import um.programacion2.libro.EstadoLibro;
import um.programacion2.prestamo.Prestamo;
import um.programacion2.usuario.Usuario;

import java.util.ArrayList;
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

    public static List<Usuario> createUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "Juan Pérez", "juanperez@gmail.com", true, new ArrayList<Prestamo>()));
        usuarios.add(new Usuario(2L, "María López", "mariaLopez@hotmail.com", false, new ArrayList<Prestamo>()));
        usuarios.add(new Usuario(3L, "Carlos García", "c.garcia@yahoo.com.ar", true, new ArrayList<Prestamo>()));
        return usuarios;
    }

}