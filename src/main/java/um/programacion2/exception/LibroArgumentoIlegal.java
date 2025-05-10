package um.programacion2.exception;

public class LibroArgumentoIlegal extends RuntimeException {
    public LibroArgumentoIlegal(String message) {
        super("El libro no se pudo crear: " + message);
    }
}
