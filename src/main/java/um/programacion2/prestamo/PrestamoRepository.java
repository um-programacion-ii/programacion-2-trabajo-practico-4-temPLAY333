package um.programacion2.prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    void save(Prestamo prestamo);
    void deleteById(Long id);
    boolean existsById(Long id);

    Optional<Prestamo> findById(Long id);
    Optional<Prestamo> findByLibroId(Long libroId);

    List<Prestamo> findAll();
}
