package um.programacion2.exception;

public class LibroNoEncontrado extends RuntimeException {
    public LibroNoEncontrado(String message) {
        super("No se encontro un libro con el ISBN de: " + message);
    }
    public LibroNoEncontrado(Long id) {
        super("No se encontro un libro con el ID de: " + id);
    }
}
