package um.programacion2.usuario;

import um.programacion2.exception.UsuarioArgumentoIlegal;

import java.util.ArrayList;

public class UsuarioFactory {

    public static Usuario createUsuario(String nombre, String email) {
        verificarNombre(nombre);
        verificarEmail(email);
        return new Usuario(null, nombre, email, true, new ArrayList<>());
    }

    public static void verificarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new UsuarioArgumentoIlegal("El nombre no puede ser nulo o vacío");
        }
    }

    public static void verificarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new UsuarioArgumentoIlegal("El email no puede ser nulo o vacío");
        }
        if (!email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new UsuarioArgumentoIlegal("El email no es válido");
        }
    }
}
