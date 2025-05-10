package um.programacion2.prestamo;

import java.util.List;

public interface PrestamoService {
    Prestamo buscarPrestamoPorId(int id);
    List<Prestamo> listarPrestamos();

    void solicitarPrestamo(Prestamo prestamo);
    void actualizarPrestamo(Prestamo prestamo);
    void devolverPrestamo(Prestamo prestamo);
}
