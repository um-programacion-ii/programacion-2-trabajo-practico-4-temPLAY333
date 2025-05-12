package um.programacion2.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import um.programacion2.prestamo.Prestamo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FechaDevolucionValidador implements ConstraintValidator<FechaDevolucionValida, Prestamo> {
    @Override
    public boolean isValid(Prestamo prestamo, ConstraintValidatorContext context) {
        if (prestamo.getFechaPrestamo() == null || prestamo.getFechaDevolucion() == null) {
            return true; // Las validaciones de nulidad se manejan con @NotNull
        }

        LocalDate fechaPrestamo = LocalDate.parse(prestamo.getFechaPrestamo());
        LocalDate fechaDevolucion = LocalDate.parse(prestamo.getFechaDevolucion());

        return ChronoUnit.DAYS.between(fechaPrestamo, fechaDevolucion) == 7;
    }
}