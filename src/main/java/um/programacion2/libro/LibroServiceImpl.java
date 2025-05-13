package um.programacion2.libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.programacion2.exception.LibroNoEncontrado;
import um.programacion2.validacion.LibroValidador;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepository;
    private final LibroValidador libroValidador;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
        this.libroValidador = new LibroValidador();
    }

    @Override
    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontrado(id));
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public void guardar(Libro libro) {
        libroValidador.verifyIsbn(libro.getIsbn());
        libroRepository.save(libro);
    }

    @Override
    public void eliminar(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        } else {
            throw new LibroNoEncontrado(id);
        }
    }

    @Override
    public void actualizar(Long id, Libro libro) {
        if (libroRepository.existsById(id)) {
            libro.setId(id);
            libroRepository.save(libro);
        } else {
            throw new LibroNoEncontrado(id);
        }
    }
}