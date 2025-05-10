package um.programacion2.usuario;

import um.programacion2.exception.IllegalArgumentsUsuario;

public class UsuarioFactory {
    private static Long idCounter = 0L;

    public static Usuario createUsuario(String nombre, String email) {
        verificarNombre(nombre);
        verificarEmail(email);
        return new Usuario(++idCounter, nombre, email, true);
    }

    public static void verificarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentsUsuario("El nombre no puede ser nulo o vacío");
        }
    }

    public static void verificarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentsUsuario("El email no puede ser nulo o vacío");
        }
        if (!email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentsUsuario("El email no es válido");
        }
    }
}
