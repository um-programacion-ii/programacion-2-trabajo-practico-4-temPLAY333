package um.programacion2.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import um.programacion2.prestamo.Prestamo;

import java.util.ArrayList;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email no válido")
    private String email;

    @NotNull
    private Boolean estado;

    @NotNull(message = "Los prestamos no pueden ser nulos")
    private ArrayList<Prestamo> prestamos;

    public boolean isActivo() {
        return estado != null && estado;
    }
}
