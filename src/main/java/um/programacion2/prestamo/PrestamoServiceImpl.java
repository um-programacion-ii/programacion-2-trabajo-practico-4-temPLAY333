package um.programacion2.prestamo;

import um.programacion2.exception.PrestamoNoEncontrado;

import java.util.List;

public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontrado(id));
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public void solicitarPrestamo(Prestamo prestamo) {
        prestamoRepository.save(prestamo);
    }

    @Override
    public void actualizarPrestamo(Prestamo prestamo) {
        if (prestamoRepository.existsById(prestamo.getId())) {
            prestamoRepository.save(prestamo);
        } else {
            throw new PrestamoNoEncontrado(prestamo.getId());
        }
    }

    @Override
    public void devolverPrestamo(Prestamo prestamo) {
        Long prestamoId = prestamo.getId();
        if (prestamoRepository.existsById(prestamoId)) {
            prestamoRepository.deleteById(prestamoId);
        } else {
            throw new PrestamoNoEncontrado(prestamoId);
        }
    }
}
