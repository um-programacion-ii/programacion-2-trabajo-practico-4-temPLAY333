package um.programacion2.prestamo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import um.programacion2.libro.Libro;
import um.programacion2.usuario.Usuario;
import um.programacion2.validacion.FechaDevolucionValida;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FechaDevolucionValida
public class Prestamo {
    private Long id;

    @NotNull(message = "El libro no puede ser nulo")
    private Libro libro;

    @NotNull(message = "El usuario no puede ser nulo")
    private Usuario usuario;

    @NotBlank(message = "La fecha de prestamo no puede ser nula")
    private String fechaPrestamo;

    @NotBlank(message = "La fecha de devolucion no puede ser nula")
    private String fechaDevolucion;
}
