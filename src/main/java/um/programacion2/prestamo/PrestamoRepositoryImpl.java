package um.programacion2.prestamo;

import java.util.List;
import java.util.Optional;

public class PrestamoRepositoryImpl implements PrestamoRepository{
    @Override
    public void guardarPrestamo(Prestamo prestamo) {
        // Implementación para guardar un préstamo
    }

    @Override
    public Optional<Prestamo> buscarPrestamoPorId(int id) {
        // Implementación para buscar un préstamo por ID
        return Optional.empty();
    }

    @Override
    public Optional<Prestamo> buscarPrestamoPorLibroId(int libroId) {
        // Implementación para buscar un préstamo por ID de libro
        return Optional.empty();
    }

    @Override
    public void eliminarPrestamo(int id) {
        // Implementación para eliminar un préstamo
    }

    @Override
    public void actualizarPrestamo(Prestamo prestamo) {
        // Implementación para actualizar un préstamo
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        // Implementación para listar todos los préstamos
        return List.of();
    }
}
