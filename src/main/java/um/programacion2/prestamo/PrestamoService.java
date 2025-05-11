package um.programacion2.prestamo;

import java.util.List;

public interface PrestamoService {
    Prestamo buscarPorId(Long id);
    List<Prestamo> listarPrestamos();

    void guardar(Prestamo prestamo);
    void actualizar(Long id, Prestamo prestamo);
    void eliminar(Long id);
}
