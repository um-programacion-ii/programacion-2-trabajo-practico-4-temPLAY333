package um.programacion2.prestamo;

import um.programacion2.exception.PrestamoNoEncontrado;

import java.util.List;

public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontrado(id));
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public void guardar(Prestamo prestamo) {
        prestamoRepository.save(prestamo);
    }

    @Override
    public void actualizar(Long id, Prestamo prestamo) {
        if (prestamoRepository.existsById(id)) {
            prestamoRepository.save(prestamo);
        } else {
            throw new PrestamoNoEncontrado(id);
        }
    }

    @Override
    public void eliminar(Long id) {
        if (prestamoRepository.existsById(id)) {
            prestamoRepository.deleteById(id);
        } else {
            throw new PrestamoNoEncontrado(id);
        }
    }
}
