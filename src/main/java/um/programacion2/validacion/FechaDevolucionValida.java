package um.programacion2.validacion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FechaDevolucionValidador.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaDevolucionValida {
    String message() default "La fecha de devolución debe ser 7 días después de la fecha de préstamo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}