package um.programacion2.prestamo;

import um.programacion2.usuario.Usuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PrestamoRepository {
    void save(Prestamo prestamo);
    void deleteById(Long id);
    void limpiarTodo();
    boolean existsById(Long id);

    Optional<Prestamo> findById(Long id);
    Optional<Prestamo> findByLibroId(Long libroId);

    List<Prestamo> findAll();

    Map<Long, Prestamo> getPrestamos();
    void setPrestamos (Map<Long, Prestamo> prestamos);

}
