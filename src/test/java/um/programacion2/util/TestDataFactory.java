package um.programacion2.util;

import um.programacion2.libro.Libro;
import um.programacion2.libro.EstadoLibro;
import um.programacion2.prestamo.Prestamo;
import um.programacion2.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Prestamo> createPrestamos() {
        List<Libro> libros = createLibros();
        List<Usuario> usuarios = createUsuarios();

        List<Prestamo> prestamos = new ArrayList<>();
        prestamos.add(new Prestamo(1L, libros.get(0), usuarios.get(0), "2023-10-20", "2023-10-27"));
        prestamos.add(new Prestamo(2L, libros.get(1), usuarios.get(1), "2023-10-07", "2023-10-14"));
        prestamos.add(new Prestamo(3L, libros.get(2), usuarios.get(2), "2023-10-10", "2023-10-17"));
        return prestamos;
    }
}