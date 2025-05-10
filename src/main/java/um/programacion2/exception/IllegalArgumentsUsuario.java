package um.programacion2.exception;

public class IllegalArgumentsUsuario extends RuntimeException {
    public IllegalArgumentsUsuario(String message) {
        super("El usuario no pudo ser creado:" + message);
    }
}
