/*
 * Este archivo define la clase REGEX, que proporciona métodos para validar texto con expresiones regulares.
 */
package Classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase REGEX proporciona métodos para validar texto con expresiones
 * regulares.
 */
public class REGEX {

    private final String RegexText = "A-Za-z-ZáéíóúÁÉÍÓÚüÜñÑ";
    private final String[] patterns = {
        /*0*/"^([" + RegexText + "\\s\'-,.()\\\"!¡?¿/%#0-9]{2,})+\\S$", // texto normal (Descripción)
        /*1*/ "^([" + RegexText + "\\s]{2,})+\\S$", // texto sin caracteres especiales
        /*2*/ "^([" + RegexText + "\\s\'-,.]{2,})+\\S$", // texto con punto y coma
        /*3*/ "^([0-9]{2}-){2}[0-9]{4}$", // Fecha dd-mm-yyyy
    };

    /**
     * Valida el texto según el tipo especificado.
     *
     * @param type El tipo de expresión regular a aplicar. (Texto con
     * caracteres, Texto alfabetico, Texto alfabrtico con caracteres , Fecha
     * dd-mm-yy)
     * @param text El texto a validar.
     * @return true si el texto coincide con el patrón, false de lo contrario.
     */
    public boolean validate(int type, String text) {
        Pattern pattern = Pattern.compile(patterns[type], Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
