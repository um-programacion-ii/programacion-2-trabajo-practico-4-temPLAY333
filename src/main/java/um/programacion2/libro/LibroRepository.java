package um.programacion2.libro;

import java.util.List;
import java.util.Optional;

public interface LibroRepository {
    void save(Libro libro);
    void deleteById(Long id);
    boolean existsById(Long id);

    Optional<Libro> findById(Long id);
    Optional<Libro> findByIsbn(String isbn);

    List<Libro> findAll();
}