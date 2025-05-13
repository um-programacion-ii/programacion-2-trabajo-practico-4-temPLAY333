package um.programacion2.libro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LibroRepository {
    void save(Libro libro);
    void deleteById(Long id);
    void limpiarTodo();
    boolean existsById(Long id);

    Optional<Libro> findById(Long id);
    Optional<Libro> findByIsbn(String isbn);

    List<Libro> findAll();

    Map<Long, Libro> getLibros();
    void setLibros (Map<Long, Libro> libros);
}