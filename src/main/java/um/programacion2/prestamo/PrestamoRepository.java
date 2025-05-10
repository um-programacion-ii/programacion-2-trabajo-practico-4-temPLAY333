package um.programacion2.prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {
    void guardarPrestamo(Prestamo prestamo);
    Optional<Prestamo> buscarPrestamoPorId(int id);
    Optional<Prestamo> buscarPrestamoPorLibroId(int libroId);
    void eliminarPrestamo(int id);
    void actualizarPrestamo(Prestamo prestamo);
    List<Prestamo> listarPrestamos();
}
