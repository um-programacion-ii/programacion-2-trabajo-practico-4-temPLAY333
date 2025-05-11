package um.programacion2.prestamo;

import um.programacion2.libro.EstadoLibro;
import um.programacion2.libro.Libro;
import um.programacion2.usuario.Usuario;
import um.programacion2.exception.PrestamoArgumentoIlegal;


public class PrestamoFactory {

    public static Prestamo createPrestamo(Libro libro, Usuario usuario) {
        verifyLibro(libro);
        verifyUsuario(usuario);

        // Poner la fecha de prestamo actual, y la fecha de devolucion 7 dias despues
        String fechaPrestamo = java.time.LocalDate.now().toString();
        String fechaDevolucion = java.time.LocalDate.now().plusDays(7).toString();

        return new Prestamo(null, libro, usuario, fechaPrestamo, fechaDevolucion);
    }

    private static void verifyLibro(Libro libro) {
        if (libro == null) {
            throw new PrestamoArgumentoIlegal("El libro no puede ser nulo");
        }
        if (libro.getEstado() != EstadoLibro.DISPONIBLE) {
            throw new PrestamoArgumentoIlegal("El libro esta " + libro.getEstado());
        }
    }

    private static void verifyUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new PrestamoArgumentoIlegal("El usuario no puede ser nulo");
        }
        if (!usuario.getEstado()) {
            throw new PrestamoArgumentoIlegal("El usuario no esta habilitado");
        }
        if (usuario.getPrestamos().size() >= 3) {
            throw new PrestamoArgumentoIlegal("El usuario ya tiene 3 prestamos");
        }
    }
}
