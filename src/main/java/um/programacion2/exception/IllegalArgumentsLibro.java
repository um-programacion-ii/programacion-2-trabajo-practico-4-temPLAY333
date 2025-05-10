package um.programacion2.exception;

public class IllegalArgumentsLibro extends RuntimeException {
    public IllegalArgumentsLibro(String message) {
        super("El libro no se pudo crear: " + message);
    }
}
