package um.programacion2.prestamo;

import java.util.List;

public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo buscarPrestamoPorId(int id) {
        return prestamoRepository.buscarPrestamoPorId(id).orElse(null);
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.listarPrestamos();
    }

    @Override
    public void solicitarPrestamo(Prestamo prestamo) {
        prestamoRepository.save(prestamo);
    }

    @Override
    public void actualizarPrestamo(Prestamo prestamo) {
        if (prestamoRepository.existsById(prestamo.getId())) {
            prestamoRepository.save(prestamo);
        }
    }

    @Override
    public void devolverPrestamo(Prestamo prestamo) {
        Long prestamoId = prestamo.getId();
        if (prestamoRepository.existsById(prestamoId)) {
            prestamoRepository.deleteById(prestamoId);
        }
    }
}
