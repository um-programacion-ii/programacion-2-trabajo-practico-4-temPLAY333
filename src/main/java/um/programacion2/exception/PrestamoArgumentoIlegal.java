package um.programacion2.exception;

public class PrestamoArgumentoIlegal extends RuntimeException {
    public PrestamoArgumentoIlegal(String message) {
        super("El prestamo no pudo ser creado:" + message);
    }
}
