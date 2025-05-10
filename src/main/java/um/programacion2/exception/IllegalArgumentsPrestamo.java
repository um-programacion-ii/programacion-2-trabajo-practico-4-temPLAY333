package um.programacion2.exception;

public class IllegalArgumentsPrestamo extends RuntimeException {
    public IllegalArgumentsPrestamo(String message) {
        super("El prestamo no pudo ser creado:" + message);
    }
}
