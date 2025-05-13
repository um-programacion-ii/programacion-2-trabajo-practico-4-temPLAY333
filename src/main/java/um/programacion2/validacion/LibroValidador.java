package um.programacion2.validacion;

import org.springframework.stereotype.Service;
import um.programacion2.exception.LibroArgumentoIlegal;

@Service
public class LibroValidador {

    public void verifyIsbn(String ISBN) throws LibroArgumentoIlegal {
        String isbnLimpio = getIsbnLimpio(ISBN);

        if (!validarDigitoControl(isbnLimpio)) {
            throw new LibroArgumentoIlegal("Codigo Erroneo (no coincide con el digito de control)");
        }
    }

    public String getIsbnLimpio(String ISBN) throws LibroArgumentoIlegal {
        String isbnLimpio = ISBN.replaceAll("[-\\s]", "");

        if (isbnLimpio.length() != 10 && isbnLimpio.length() != 13) {
            throw new LibroArgumentoIlegal("No tiene 10 ni 13 dígitos");
        }

        // Verificar que solo contenga dígitos (y posiblemente 'X' en la última posición para ISBN-10)
        if (!isbnLimpio.matches("[0-9]{9}[0-9X]") && !isbnLimpio.matches("[0-9]{13}")) {
            throw new LibroArgumentoIlegal("Contiene caracteres inválidos");
        }
        return isbnLimpio;
    }

    public boolean validarDigitoControl(String isbnLimpio) {
        int suma = 0;
        if (isbnLimpio.length() == 10) {
            // Validación para ISBN-10
            for (int i = 0; i < 9; i++) {
                suma += (isbnLimpio.charAt(i) - '0') * (10 - i);
            }

            char ultimoCaracter = isbnLimpio.charAt(9);
            int digitoControl = (ultimoCaracter == 'X') ? 10 : (ultimoCaracter - '0');

            return (suma + digitoControl) % 11 == 0;
        } else {
            // Validación para ISBN-13
            for (int i = 0; i < 12; i++) {
                suma += (isbnLimpio.charAt(i) - '0') * (i % 2 == 0 ? 1 : 3);
            }

            int digitoControl = isbnLimpio.charAt(12) - '0';
            int digitoCalculado = (10 - (suma % 10)) % 10;

            return digitoControl == digitoCalculado;
        }
    }
}
