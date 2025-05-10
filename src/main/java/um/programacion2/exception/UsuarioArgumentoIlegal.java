package um.programacion2.exception;

public class UsuarioArgumentoIlegal extends RuntimeException {
    public UsuarioArgumentoIlegal(String message) {
        super("El usuario no pudo ser creado:" + message);
    }
}
