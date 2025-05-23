package um.programacion2.libro;

import java.util.List;

public interface LibroService {
    Libro buscarPorId(Long id);
    List<Libro> listarLibros();

    void guardar(Libro libro);
    void actualizar(Long id, Libro libro);
    void eliminar(Long id);
}