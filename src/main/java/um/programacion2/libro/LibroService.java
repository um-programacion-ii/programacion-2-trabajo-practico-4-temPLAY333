package um.programacion2.libro;

import java.util.List;

public interface LibroService {
    Libro buscarPorIsbn(String isbn);
    List<Libro> obtenerTodos();

    void guardar(Libro libro);
    void actualizar(Long id, Libro libro);
    void eliminar(Long id);
}