package um.programacion2.exception;

public class PrestamoNoEncontrado extends RuntimeException {
    public PrestamoNoEncontrado(String message) {
        super(message);
    }
    public PrestamoNoEncontrado(Long id) {
        super("Prestamo no encontrado con ID: " + id);
    }
}
