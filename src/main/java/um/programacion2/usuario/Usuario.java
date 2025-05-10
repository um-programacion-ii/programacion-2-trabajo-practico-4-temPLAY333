package um.programacion2.usuario;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import um.programacion2.prestamo.Prestamo;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private Boolean estado;
    private ArrayList<Prestamo> prestamos;
}
