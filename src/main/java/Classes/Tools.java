/*
 * Este archivo define la clase Tools, que proporciona métodos para convertir listas de cadenas a formato Tools.
 */
package Classes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * La clase Tools proporciona métodos para convertir listas de cadenas a formato
 * Tools.
 */
public class Tools {

    /**
     * Convierte una lista de cadenas a un objeto JSONArray en formato Tools.
     *
     * @param list La lista de cadenas a convertir.
     * @return El objeto JSONArray resultante (Retorna null en caso de error).
     */
    public static JSONArray listToJson(List<String> list) {
        JSONArray jsonArray;
        String temp = "";
        try {
            if (list == null) {
                temp = "";
            } else if (list.size() == 1) {
                temp = "\"" + list.get(0) + "\"";
            } else {
                for (String t : list) {
                    temp += ",\"" + t + '"';
                }
                temp = temp.substring(1);
            }
            jsonArray = new JSONArray("[" + temp + "]");
        } catch (JSONException e) {
            Window.Message("danger", "JSON Error:\n" + e.toString(), "JSON Error");
            return null; // Manejar el error según sea necesario
        }
        return jsonArray;
    }
    private final static String[] patterns = {
        /*0*/"^([A-Za-z-ZáéíóúÁÉÍÓÚüÜñÑ\\s\'-,.;:()\\\"!¡?¿/%#0-9]{2,})+\\S$", // texto normal (Descripción)
        /*1*/ "^([A-Za-z-ZáéíóúÁÉÍÓÚüÜñÑ;\\-_?¿!¡:\\s]{2,})$", // texto sin caracteres especiales
        /*2*/ "^([A-Za-z-ZáéíóúÁÉÍÓÚüÜñÑ\\s\'-,.]{2,})+\\S$", // texto con punto y coma
        /*3*/ "^([0-9]{2}-){2}[0-9]{4}$", // Fecha dd-mm-yyyy
        /*4*/ "^([A-Za-z-ZáéíóúÁÉÍÓÚüÜñÑ\\d]{4,})$",//UserName
        /*5*/ "^mongodb://(?:[a-zA-Z0-9-_.]+(?::[a-zA-Z0-9-_.]+)?@)?(?:[a-zA-Z0-9-]+\\.)*[a-zA-Z0-9-]+(?::\\d{1,5})?(?:\\/[\\w-]+)*(?:\\?([\\w-]+=[\\w-]+(?:&[\\w-]+=[\\w-]+)*)?)?(?:#[a-zA-Z0-9!$&'()*+,;=:@._-]*)?$", /**/};

    private static String pattern(String type) {
        int t;
        switch (type.toLowerCase()) {
            case "uri":
                t = 5;
                break;
            case "dbname":
            case "username":
                t = 4;
                break;
            case "date":
                t = 3;
                break;
            case "name":
                t = 1;
                break;
            case "password":
            case "description":
                t = 0;
                break;
            case "actors":
                t = 2;
                break;
            default:
                t = 0;
                break;
        }
        return patterns[t];
    }

    /**
     * Valida el texto según el tipo especificado.
     *
     * @param type El tipo de expresión regular a aplicar.
     * @param text El texto a validar.
     * @return true si el texto coincide con el patrón, false de lo contrario.
     */
    public static boolean validate(String type, String text) {
        Pattern pattern = Pattern.compile(pattern(type), Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
