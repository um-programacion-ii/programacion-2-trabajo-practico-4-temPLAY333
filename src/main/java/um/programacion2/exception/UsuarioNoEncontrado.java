package um.programacion2.exception;

public class UsuarioNoEncontrado extends RuntimeException {
    public UsuarioNoEncontrado(String message) {
        super("Usuario no encontrado con email: " + message);
    }
    public UsuarioNoEncontrado(Long id) {
        super("Usuario no encontrado con ID: " + id);
    }
}
